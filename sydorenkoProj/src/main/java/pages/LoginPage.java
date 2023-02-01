package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Can not open Login page" + e);
            Assert.fail("Can not open Login page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
//        try {
//            WebElement inputUserName = webDriver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']"));
//            inputUserName.clear();
//            inputUserName.sendKeys(userName);
//            logger.info("Login was inputted");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    public boolean isButtonSignInDisplayed() {
        return isButtonDisplayed(buttonLogin);
    }
}
