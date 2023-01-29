package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@name='password' and @placeholder='Password']")
    private WebElement inputPassword;

    @FindBy (xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy (xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement errorMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Login page was opened");

        } catch (Exception e) {
            logger.error("Can not open Login page" + e);
            Assert.fail("Can not open Login page" + e);
        }

    }

    public void enterUserNameIntoInputLogin(String userName) {
            enterTextIntoElement(inputUserName, userName);

    }

    public void enterPasswordIntoInputPassword(String password) {
            enterTextIntoElement(inputPassword, password);
    }

    public void clickButtonLogin() {
       clickElement(buttonLogin);
    }

   public boolean isErrorMessageDisplayed () {
       try {
           return isElementDisplayed(errorMessage);
       } catch (Exception e) {
           printErrorAndStopTest(e);
           return false;
       }
   }
}
