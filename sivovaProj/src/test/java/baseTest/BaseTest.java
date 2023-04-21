package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import libs.ScreenShot;
import org.apache.log4j.Logger;
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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.HomePage;
import pages.LoginPage;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

public class BaseTest {

    protected WebDriver webdriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();

    @Before
    public void setUp(){
        logger.info("------ " + testName.getMethodName()+ " was started -------");
        WebDriverManager.chromedriver().setup();
        webdriver = initDriver();
        webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(webdriver);
        homePage = new HomePage(webdriver);
    }

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

            if (webdriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            byte[] screen = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
            listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
            saveScreenshot(listOfScreenShots);
        }


        @Override

        protected void finished(Description description) {
            logger.info(
                    String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {

                webdriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }

    };


    protected void takeScreenshot() {
        System.out.println("screenshot was taken");
        byte[] screen = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
        listOfScreenShots.add(new ScreenShot(testName.getMethodName() + "_after", screen));

    }


//    @After
//    public void tearDown(){
//        webdriver.quit();
//        logger.info("Browser was closed");
//        logger.info("------ " + testName.getMethodName()+ " was ended -------");
//        WebDriverManager.chromedriver().setup();
//    }

    @Rule
    public TestName testName = new TestName();

    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if ((browser == null) || "chrome".equalsIgnoreCase(browser)){
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            webdriver = new ChromeDriver(ops);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webdriver = new FirefoxDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            WebDriverManager.safaridriver().setup();
            webdriver = new SafariDriver();
        } else if ("ie".equalsIgnoreCase(browser)) {
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            webdriver = new EdgeDriver();
        } else if ("remote".equals(browser)) {

            WebDriverManager.chromedriver().setup();

            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setBrowserName("chrome");

            //            cap.setPlatform(Platform.WINDOWS);

            //            cap.setVersion("79");

            ChromeOptions chromeOptions = new ChromeOptions();

            chromeOptions.merge(cap);

            try {

                webdriver = new RemoteWebDriver(

                        new URL("http://localhost:4444/wd/hub"),

                        chromeOptions);

            } catch (MalformedURLException e) {

                e.printStackTrace();

            }

        }

        return webdriver;
    }

}
