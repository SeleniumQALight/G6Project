
package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> errorMessages;

    private String messageAlert = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
        return this;
    }

    public LoginPage enterUserNameIntoInpuLogin(String userName) {
        enterTextInToElement(inputUserName, userName);
        return this;
    }


    public void enterPasswordIntoInputPassword(String password) {
        enterTextInToElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);

    }
    public boolean isSignInButtonDisplayed(){
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithValidCred() {
        openLoginPage();
        enterUserNameIntoInpuLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSPORT);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public LoginPage enterUserNameIntoInpuPick_a_username(String   pickUserName) {
        enterTextInToElement(pick_a_username, pickUserName);
        return this;
    }

    public LoginPage enterEmailIntoInputEmail(String email) {
        enterTextInToElement(emailElement, email);
        return this;
    }

    public LoginPage enterPasswordIntoInputCreate_a_password(String password) {
        enterTextInToElement(create_a_password, password);
        return this;
    }

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

    public LoginPage checkAlertMessagesContent(String expectedMessage) {
        Assert.assertTrue("Element with text '"+expectedMessage+"' is not found.", isElementDisplayed(String.format(messageAlert, expectedMessage)));
        return this;
    }

    public LoginPage checknumberOfMessages (int numberOfElements) {
            Assert.assertEquals("Number of elements does not match.", numberOfElements, errorMessages.size());
            logger.info("Number of Elements matches.");
            return this;

    }
}
