package baseTest;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
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
        webDriver = initDriver();

//        WebDriverManager.chromedriver().setup();   // запуск браузера Хром
//        webDriver = new ChromeDriver();
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
    public TestName testName = new TestName();  // працює з 33 та 54

    private WebDriver initDriver() {                       //
        String browser = System.getProperty("browser");
        if ((browser==null) || "chrome".equalsIgnoreCase(browser)){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browser)){
            WebDriverManager.edgedriver().setup();
            webDriver=new EdgeDriver();
        } else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();
            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }

        return webDriver;
    }

}
