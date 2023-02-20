package pages;

import libraries.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    private String messageAlert = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

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
        } catch (Exception e) {
            logger.error("Cannot open Login Page!" + e);
            Assert.fail("Cannot open Login Page!" + e);
        }
        return this;
    }

    public void enterUserNameIntoInputLogin(String userName) {
        enterTextToElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonLogIn() {
        clickOnElement(buttonLogin);
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
        Assert.assertTrue("Element with text '"+expectedMessage+"' is not found.", isElementDisplayed(String.format(messageAlert, expectedMessage)));
        return this;
    }

}