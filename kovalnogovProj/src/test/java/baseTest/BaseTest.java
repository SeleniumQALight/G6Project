package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;


import java.time.Duration;

public class BaseTest {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger( getClass() );
    protected LoginPage loginPage ;

    @Before
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 5 ) );
        loginPage = new LoginPage( webDriver );
    }


    @After
    public void afterTest() {
        webDriver.quit();
        logger.info( "Browser is closed" );
    }
}
