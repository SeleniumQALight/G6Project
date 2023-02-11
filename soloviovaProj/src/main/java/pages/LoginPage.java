package pages;

import libraries.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
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

    private String alertMessage = ".//*[text()='%s']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
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
        openLoginPage();
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

    public LoginPage checkIsAlertMessagesDisplayed() {
        List<WebElement> elements = webDriver.findElements(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        Assert.assertEquals("One of the elements is not found", 3, elements.size());
        logger.info("Three alert messages are displayed");
        return this;
    }

    public WebElement getAlertMessage(String message) {
        return webDriver.findElement(By.xpath(String.format(alertMessage, message)));
    }

    public LoginPage checkAlertMessagesContent() {
        Assert.assertEquals("Alert for User Name field does not match", TestData.MESSAGE_1, getAlertMessage("Username must be at least 3 characters.").getText());
        logger.info("Alert for User Name field matches");
        Assert.assertEquals("Alert for Email Field does not match", TestData.MESSAGE_2, getAlertMessage("You must provide a valid email address.").getText());
        logger.info("Alert for Email Field matches");
        Assert.assertEquals("Alert for Password Field does not match", TestData.MESSAGE_3, getAlertMessage("Password must be at least 12 characters.").getText());
        logger.info("Alert for Password Field matches");
        return this;
    }

}