
package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(xpath = ".//input[@name='username' and @id='username-register']")
    private WebElement pick_a_username;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement emailElement;

    @FindBy(xpath = ".//input[@name='password' and @id='password-register']")
    private WebElement create_a_password;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSign_up_for_OurApp;

    @FindBy(xpath = ".//*[text() = 'Username must be at least 3 characters.']")
    private WebElement usernameMessage;

    @FindBy(xpath = ".//*[text() = 'You must provide a valid email address.']")
    private WebElement emailMessage;

    @FindBy(xpath = ".//*[text() = 'Password must be at least 12 characters.']")
    private WebElement passwordMessage;

    private static final String errorListXpath = ".//*[@class='alert alert-danger small liveValidateMessage "
            + "liveValidateMessage--visible']";

    @FindBy(xpath = errorListXpath)
    private List<WebElement> errorMessages;

    private String messageAlert = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    private static final String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;

    @Override
    String getRelatedURL() {
        return "/";
    }
@Step
    public LoginPage openLoginPage() {
        try {
            webDriver.get(base_url + getRelatedURL());
            logger.info("LoginPage was opened");
            logger.info(base_url);
        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
        return this;
    }
    @Step
    public LoginPage enterUserNameIntoInpuLogin(String userName) {
        enterTextInToElement(inputUserName, userName);
        return this;
    }

    @Step
    public void enterPasswordIntoInputPassword(String password) {
        enterTextInToElement(inputPassword, password);
    }
    @Step
    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);

    }
    @Step
    public boolean isSignInButtonDisplayed(){
        return isElementDisplayed(buttonLogin);
    }
    @Step
    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInpuLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSPORT);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage enterUserNameIntoInpuPick_a_username(String   pickUserName) {
        enterTextInToElement(pick_a_username, pickUserName);
        return this;
    }
    @Step
    public LoginPage enterEmailIntoInputEmail(String email) {
        enterTextInToElement(emailElement, email);
        return this;
    }
    @Step
    public LoginPage enterPasswordIntoInputCreate_a_password(String password) {
        enterTextInToElement(create_a_password, password);
        return this;
    }
    @Step
    public void clickOnSign_up_for_OurApp() {
        clickOnElement(buttonSign_up_for_OurApp);
    }

//    public LoginPage checkIsUsernameMessageDisaplyed() {
//        isElementDisplayed(usernameMessage);
//        return this;
//    }
//
//    public LoginPage checkIsEmailMessageDisaplyed() {
//        isElementDisplayed(emailMessage);
//        return this;
//    }
//
//    public LoginPage checkIsPasswordMessageDisaplyed() {
//        isElementDisplayed(passwordMessage);
//        return this;
//    }
@Step
    public LoginPage checkAlertMessagesContent(String expectedMessage) {
        Util.waitABit(1);
        Assert.assertTrue("Element with text '"+expectedMessage+"' is not found.", isElementDisplayed(String.format(messageAlert, expectedMessage)));
        return this;
    }
    @Step
    public LoginPage checknumberOfMessages (int numberOfElements) {
        webDriverWait15.until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorListXpath),numberOfElements));
            Assert.assertEquals("Number of elements does not match.", numberOfElements, errorMessages.size());
            logger.info("Number of Elements matches.");
            return this;

    }
    @Step
    public LoginPage checkRegistrationErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10.withMessage("Number of messages should be " + expectedErrorsArray.length).until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages ", expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equals ").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }

    public void checkAlertInCenter(String expectedText) {
        Assert.assertEquals("Message in Alert", expectedText, alertInCenter.getText());
    }
}
