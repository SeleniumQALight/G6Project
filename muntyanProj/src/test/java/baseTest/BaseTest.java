package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;

import java.time.Duration;

public class BaseTest {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected MyProfilePage myProfilePage;

    @Before
    public void setUp() {
        logger.info("--------- " + testName.getMethodName() + " was started ----------");
        webDriver = initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);

    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("-------- " + testName.getMethodName() + " was ended -----------");
    }

    @Rule
    public TestName testName = new TestName();

    private WebDriver initDriver() {
        String browser = System.getProperty("browser");
        if ((browser == null) || "chrome".equalsIgnoreCase((browser))) {
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(ops);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        } else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();
            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }

        return webDriver;
    }


}
