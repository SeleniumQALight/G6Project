package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class loginTest {
    WebDriver webDriver;
    @Test   // show that methods after  @Test  can be run
    public void validLogin() {   // separate test
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();     // open new Chrome

        webDriver.manage().window().maximize();       // full screen open
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // ask to wait 5 seconds (it will do commands each 0.5 sec during 5 second in total)
        System.out.println("Browser opened");
        webDriver.get("https://qa-complexapp.onrender.com/");  // opened a link
        System.out.println("Page site opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        // Write down element under name "inputUserName" which we set with help of xPath
        inputUserName.clear();        // Clear field
        inputUserName.sendKeys("qaauto");  // Enter mentioned data into field
        System.out.println("login was inputted");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inputted");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("button was clicked");

//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));

        Assert.assertTrue("Button is not displayed", isButtonSignOutDisplayed() );



        webDriver.quit();
        System.out.println("Browser was closed");
    }
    @Test
    public void notValidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("Browser opened");

        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("Page site opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaautoAAA");
        System.out.println("login was inputted");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inputted");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");

        WebElement loginError = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
        System.out.println("Error message appeared");

//        buttonSignIn.isDisplayed();
//        System.out.println("SignIn button still present");

//        WebElement buttonSignInAfterError = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
//        System.out.println("SignIn button still present");

        Assert.assertTrue("Button is not displayed", isButtonSignInDisplayed() );

        webDriver.quit();
        System.out.println("Browser was closed");

    }

    private boolean isButtonSignOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isButtonSignInDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
