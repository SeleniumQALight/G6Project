package pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Login page was open");
        } catch (Exception e) {
            logger.error("Can't Open page " + e);
            Assert.fail("Can't Open page " + e);
        }
        return this;
    }

    public void typeUserName(String userName) {
        try {
            WebElement inputUserName =
                    webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
            inputUserName.clear();
            inputUserName.sendKeys( userName );
logger.info("Type User name ");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    public void typeUserPassword(String password) {
        try {
            WebElement passwordInput =
                    webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            passwordInput.clear();
            passwordInput.sendKeys( password );
            logger.info("Type password ");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }
    public HomePage clickSignIn() {
        try {
            WebElement signInBtn =
                    webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
            signInBtn.click();
            logger.info("Sign in clicked");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
        return new HomePage(webDriver);
    }
}
