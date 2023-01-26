package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ExecutionException;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
        try {
            WebElement inputUserName =
                    webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
            inputUserName.clear();
            inputUserName.sendKeys(userName);
            logger.info("login was inputted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterLoginNameIntoInputPassword(String password) {
        try {
            WebElement irputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            irputPassword.clear();
            irputPassword.sendKeys(password);
            logger.info("Password was inputted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonLogin() {
        try {
            WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
            buttonLogin.click();
            logger.info("button was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
}
