package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginPage extends ParentPage {

    public LoginPage( WebDriver webDriver ) {
        super( webDriver );
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get( "https://qa-complexapp.onrender.com/" );
            logger.info("Login page was open");
        } catch ( Exception e ) {
            logger.error( "Can't Open page " + e );
            Assert.fail( "Can't Open page " + e );
        }
        return this;
    }


}
