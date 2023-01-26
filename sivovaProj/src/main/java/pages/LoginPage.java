package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {


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
        try {
            WebElement inputUserName =
                    webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
            inputUserName.clear();
            inputUserName.sendKeys(userName);
            logger.info("Login was inputted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    public void enterPasswordIntoInputPassword(String password) {
        try {
            WebElement inputPassword =
                    webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"));
            inputPassword.clear();
            inputPassword.sendKeys(password);
            logger.info("Password was inputted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickButtonLogin() {
        try{
            WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
            buttonLogin.click();
            logger.info("Button was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
}
