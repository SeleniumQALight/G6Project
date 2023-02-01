package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement signInButton;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Cannot open Login Page" + e);
            Assert.fail("Cannot open Login Page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String username) {
        enterTextInToElement(inputUserName, username);

    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextInToElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }


    public boolean isSignInButtonDisplayed() {
       return isElementPresented(signInButton);
    }


}
