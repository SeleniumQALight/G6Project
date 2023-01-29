package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginPage extends ParentPage{
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Page was opened");
        }catch(Exception e){
            logger.error("Can't open Login Page" + e);
            Assert.fail("Can't open Login Page" + e);
        }
    }
}
