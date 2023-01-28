package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Loggin page is opened.");
        } catch (Exception e) {
            logger.error("Cannot open Login Page!" + e);
            Assert.fail("Cannot open Login Page!" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
        try {
            WebElement inputUserName =
                    webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
            inputUserName.clear();
            inputUserName.sendKeys(userName);
            logger.info("Login is entered");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPasswordIntoInputPassword(String password) {
        try {
            WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"));
            inputPassword.clear();
            inputPassword.sendKeys(password);
            logger.info("Password is entered");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonLogIn() {
        try {
            WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
            buttonLogin.click();
            logger.info("Button is clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
}