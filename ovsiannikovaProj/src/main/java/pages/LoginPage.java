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
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Cannot open Login Page" + e); //writes message in log file
            Assert.fail("Cannot open Login Page" + e); //writes message in console and in report file
        }
    }

    public void enterUserNameIntoInputLogin(String username) {
        try {
            WebElement inputUserName =
                    webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
            inputUserName.clear();
            inputUserName.sendKeys(username);
            logger.info("login was inputted");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void enterUserNameIntoInputPassword(String password) {
        try {
            WebElement inputPassword =
                    webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            inputPassword.clear();
            inputPassword.sendKeys(password);
            logger.info("Password was entered");

        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonLogin() {
        try {
            WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
            buttonLogin.click();
            logger.info("Button was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
}
