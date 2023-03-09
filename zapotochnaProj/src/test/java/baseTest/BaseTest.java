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
import pages.HomePage;
import pages.LoginPage;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;

public class BaseTest { //батьківський клас для всіх класів, все що відноситься до тестів
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;

    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();

    @Before //pre-cond
    public void setUp() {
        logger.info("-------- " + testName.getMethodName() + " --------");

        webDriver = initDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(webDriver);


        homePage = new HomePage(webDriver);

    }


//    @After
//    public void tearDown() {
//        webDriver.quit();
//        logger.info("browser closed");
//
//        logger.info("test is ended " + testName.getMethodName() + " --------");
//
//    }

    @Rule // ця анотація запускається незалежно. перед раном тесту, запише в змінну ім'я тесту який зараз запускається
    public TestName testName = new TestName();

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
    private WebDriver initDriver() {

        String browser = System.getProperty("browser"); // передаємо цю зміну при запуску тесту  , якщо не передаємо по-дефолту буде null
        if ((browser == null) || "chrome".equalsIgnoreCase(browser)) {


            //якщо перестане працювати Хром післі апдейту, розкоментувати ці рядочки
//            ChromeOptions ops = new ChromeOptions();
//            ops.addArguments("--remote-allow-origins=*");
//            WebDriverManager.chromedriver().setup();
//            webDriver = new ChromeDriver(ops);

            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();

        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }
//        else if ("ie".equalsIgnoreCase(browser)) {
//            //WebDriverManager.iedriver().setup();
//            // in most cases 32bit version is needed
//            WebDriverManager.iedriver().arch32().setup();
//            return new InternetExplorerDriver();
//        }
        return webDriver;
    }
}
