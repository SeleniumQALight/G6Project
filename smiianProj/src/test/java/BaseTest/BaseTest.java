package BaseTest;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.elements.HeaderElement;

import java.time.Duration;

public class BaseTest {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass()); //logger from apache
    protected LoginPage loginPage;
    protected HomePage homePage;


    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);


    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Closed");

    }
}
