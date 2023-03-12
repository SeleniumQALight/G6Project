package pages;

import libraries.TestData;
import libraries.Util;
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
    @FindBy(xpath = ".//input[@name='password' and @placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Pick a username']")
    private WebElement userNameForRegistration;
    @FindBy(xpath = ".//input[@name='email' and @id='email-register']")
    private WebElement emailForRegistration;
    @FindBy(xpath = ".//input[@name='password' and @placeholder='Create a password']")
    private WebElement passwordForRegistration;
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement signUpButton;
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> errorMessages;
    @FindBy(xpath = ".//*[@class = 'alert alert-danger text-center']")
    private WebElement errorMessageForLogIn;

    private String messageAlert = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";
    private static final String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("Loggin page is opened.");
            logger.info(base_url);
        } catch (Exception e) {
            logger.error("Cannot open Login Page!" + e);
            Assert.fail("Cannot open Login Page!" + e);
        }
        return this;
    }

    public LoginPage enterUserNameIntoInputLogin(String userName) {
        enterTextToElement(inputUserName, userName);
        return this;
    }

    public LoginPage enterPasswordIntoInputPassword(String password) {
        enterTextToElement(inputPassword, password);
        return this;
    }

    public LoginPage clickOnButtonLogIn() {
        clickOnElement(buttonLogin);
        return this;
    }

    public boolean isSignInButtonDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithWalidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PAsSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }

    //-------------------------------------
    public LoginPage enterUserNameToRegister(String username) {
        enterTextToElement(userNameForRegistration, username);
        return this;
    }

    public LoginPage enterEmailToRegister(String email) {
        enterTextToElement(emailForRegistration, email);
        return this;
    }

    public LoginPage enterPasswordToRegister(String password) {
        enterTextToElement(passwordForRegistration, password);
        return this;
    }

    public LoginPage clickSignUpButton() {
        clickOnElement(signUpButton);
        return this;
    }

    public LoginPage checkIsAlertMessagesDisplayed(int numberOfElements) {
        Assert.assertEquals("Number of elements does not match.", numberOfElements, errorMessages.size());
        logger.info("Number of Elements matches.");
        return this;
    }

    public LoginPage checkAlertMessagesContent(String expectedMessage) {
        Assert.assertTrue("Element with text '" + expectedMessage + "' is not found.", isElementDisplayed(String.format(messageAlert, expectedMessage)));
        return this;
    }

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

    public LoginPage checkErrorMessageForLogIn(String errorMessage){
        Assert.assertTrue("Element is not dispalyed", isElementDisplayed(errorMessageForLogIn));
        Assert.assertEquals("Message is not found its match", errorMessage, errorMessageForLogIn.getText());
        return this;
    }
}