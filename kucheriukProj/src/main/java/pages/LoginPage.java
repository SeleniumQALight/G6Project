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

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement userNameRegistrationField;

    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement passwordRegistrationField;

    String errorMessage = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    String listOfErrorsLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> notificationsWithErrors;

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
            logger.info(base_url);
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    @Step
    public void enterUserNameIntoInputLogin(String userName) {
//
        enterTextInToElement(inputUserName, userName);
    }

    @Step
    public void enterPasswordIntoInputPassword(String password) {
//        try {
//            //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("Password was entered");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputPassword, password);
    }

    @Step
    public void clickOnButtonLogin() {
//        try{
//            //WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
//            buttonLogin.click();
//            logger.info("Button was clicked");
//        }catch(Exception e){
//            printErrorAndStopTest(e);
//        }
//    }
        clickOnElement(buttonLogin);
    }

    @Step
    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    @Step
    public HomePage fillingLoginForWithValidCred() {
        //openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterUserNameIntoRegistrationField(String userName) {
        enterTextInToElement(userNameRegistrationField, userName);
        return this;
    }

    @Step
    public LoginPage enterEmailIntoEmailField(String email) {
        enterTextInToElement(emailField, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoPasswordRegistrationField(String password) {
        enterTextInToElement(passwordRegistrationField, password);
        return this;
    }

    @Step
    public boolean isFieldValidationErrorIsDisplayed(String error) {
        return isElementDisplayed(errorMessage, error);
    }

    @Step
    public LoginPage checkErrorCountMessage(int quantityErrors) {
        webDriverWait10
                .withMessage("Number of message should be " + quantityErrors)
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(listOfErrorsLocator)
                                , quantityErrors));
        Assert.assertEquals("The number of errors is not as expected",
                quantityErrors, notificationsWithErrors.size());
        return this;
    }

    @Step
    public LoginPage checkErrorsMessage(String expectedErrors) {
        //error1, error2 -> array[0] = error1, array[1] = error2
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of message should be " + expectedErrorsArray.length)
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(listOfErrorsLocator)
                                , expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of message", expectedErrorsArray.length, notificationsWithErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element:notificationsWithErrors){
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equals ")
                    .isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }

    public void checkAlertInCenter(String expectedText) {
        Assert.assertEquals("Message in Alert", expectedText, alertInCenter.getText());
    }
}




