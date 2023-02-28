package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

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

}
