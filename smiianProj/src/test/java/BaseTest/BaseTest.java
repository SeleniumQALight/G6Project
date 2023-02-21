package BaseTest;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.PostEditPage;
import pages.elements.HeaderElement;

import java.time.Duration;

public class BaseTest {

    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass()); //logger from apache

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected HeaderElement headerElement;
    protected PostEditPage postEditPage;



    @Before
    public void setUp(){
        logger.info("------ " + testName.getMethodName()+ " was started------");    // для гарного логування даних по кейсу на початку логу

        WebDriverManager.chromedriver().setup();   // запуск браузера Хром
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();    // відкриваємо вікно на весь екран
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        headerElement = new HeaderElement(webDriver);
        postEditPage = new PostEditPage(webDriver);


    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Closed");

        logger.info("------ " + testName.getMethodName() + "was ended------");      // для гарного логування даних по кейсу в кінці логу
    }

    @Rule
    public TestName testName = new TestName();

}
