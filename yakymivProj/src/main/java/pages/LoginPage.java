package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPass;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("Page was opened");
        } catch (Exception e) {
            logger.error("Can't open Login Page " + e);
            Assert.fail("Can't open Login Page " + e);
        }
    }

    public void enterUserNameintoInputLogin(String userName) {
        enterTextToElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextToElement(inputPass, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonSignIn);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameintoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public HomePage fillingLoginFormWithInvalidCred() {
        openLoginPage();
        enterUserNameintoInputLogin(TestData.INVALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.INVALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public boolean isButtonSignInDisplayed() {
        return isButtonDisplayed(buttonSignIn);
    }
}
