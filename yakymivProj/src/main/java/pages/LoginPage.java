package pages;

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

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Page was opened");
        } catch (Exception e) {
            logger.error("Can't open Login Page " + e);
            Assert.fail("Can't open Login Page " + e);
        }
    }

    public void enterUserNameintoInputLogin(String userName) {
        enterTextToElement(inputUserName,userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextToElement(inputPass,password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonSignIn);
    }
}
