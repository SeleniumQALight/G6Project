package invalidLogIn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private WebDriver webDriver;

    @Test
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();  // це відкриття браузера хрома
        webDriver.manage().window().maximize(); // make a window full screen
        webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        System.out.println("browser was opened");

        webDriver.get("https://qa-complexapp.onrender.com/"); // open website
        System.out.println("Site was open");
        WebElement inputUserName =
                webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        System.out.println("login was inputed");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inputted");

        WebElement buttonSignIn =
                webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");


        Assert.assertTrue("Button is not displayed"
                , isButtonSignOutDisplayed());


        webDriver.quit();
        System.out.println("Browser was closed");
    }

    @Test
    public void invalidLogin() {
    WebDriverManager.chromedriver().setup();
    webDriver = new ChromeDriver();  // це відкриття браузера хрома
        webDriver.manage().window().maximize(); // make a window full screen
        webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        System.out.println("browser was opened");

        webDriver.get("https://qa-complexapp.onrender.com/"); // open website
        System.out.println("Site was open");
    WebElement inputUserName =
            webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        System.out.println("login was inputed");

    WebElement inputPassword =
            webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("12345");
        System.out.println("password was inputted");

    WebElement buttonSignIn =
            webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");


        Assert.assertTrue("Button is not displayed"
                , isButtonSignInDisplayed());


        webDriver.quit();
        System.out.println("Browser was closed");
}

    private boolean isButtonSignOutDisplayed() {

        try {
            return webDriver.findElement(By.xpath(".//button[@class ='btn btn-sm btn-secondary']")).isDisplayed();
        } catch (Exception e) {

        }return false;
    }

    private boolean isButtonSignInDisplayed() {

        try {
            return webDriver.findElement(By.xpath(".//button[not(@type='submit')]")).isDisplayed();
        } catch (Exception e) {

        }return false;
    }

}
