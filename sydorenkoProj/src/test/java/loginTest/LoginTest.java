package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("browser was opened");
    }

    @After
    public void tearDown() {
        webDriver.quit();
        System.out.println("browser was closed");
    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void validLogin() {

        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        System.out.println("login was inputed");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@name='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inputed");

        WebElement buttonSignIn = webDriver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("button was clicked");

        assertTrue("Button is not displayed", isButtonSignOutDisplayed());

    }

    @Test
    public void inValidLogin() {

        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        System.out.println("login was inputed");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@name='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty11");
        System.out.println("password was inputed");

        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']]"));
        buttonSignIn.click();
        System.out.println("button was clicked");

        assertTrue("Button is displayed", !isButtonSignOutDisplayed());
        assertTrue("Button is not displayed", isButtonSignInDisplayed());

    }

    private boolean isButtonSignOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    private boolean isButtonSignInDisplayed() {
        try {
            return webDriver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
