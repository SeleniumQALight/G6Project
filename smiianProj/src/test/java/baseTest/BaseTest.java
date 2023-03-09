package baseTest;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import libs.ScreenShot;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.PostEditPage;
import pages.elements.HeaderElement;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;

public class BaseTest {

    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass()); //logger from apache

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected HeaderElement headerElement;
    protected PostEditPage postEditPage;

    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();



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


//-----------------------------------------------------------------------start of code For adding Screenshots
    @Rule(order = Integer.MIN_VALUE)
    public final TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        public void saveScreenshot(ArrayList<ScreenShot> screenShots) {
            screenShots.forEach(screenShot -> Allure.addAttachment(screenShot.getName(),
                    new ByteArrayInputStream(screenShot.getScreenShotImg())));
        }

        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
            saveScreenshot(listOfScreenShots);
        }

        @Override
        protected void finished(Description description) {
            logger.info(
                    String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }

    };


    protected void takeScreenshot() {
        System.out.println("screenshot was taken");
        byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        listOfScreenShots.add(new ScreenShot(testName.getMethodName() + "_after", screen));
    }

//-----------------------------------------------------------------------------------------------------end of code For adding Screenshots



    private WebDriver initDriver() {                       //
        String browser = System.getProperty("browser");
        if ((browser==null) || "chrome".equalsIgnoreCase(browser)){

            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(ops);

//            WebDriverManager.chromedriver().setup();
//            webDriver = new ChromeDriver();
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
