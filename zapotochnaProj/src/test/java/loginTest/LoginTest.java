package loginTest;

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
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        System.out.println("browser was opened");
        webDriver.get("https://qa-complexapp.onrender.com/");


        WebElement inputUserName =
                webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));

        inputUserName.clear();
        inputUserName.sendKeys("qaauto");

        System.out.println("login was entered");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");

        System.out.println("password was entered");

        WebElement buttonSignIn =
                webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();

        System.out.println("button was clicked");


        Assert.assertTrue("button is not displayed", isButtonSignOutDisplayed());


        webDriver.quit();
        System.out.println("browser was closed");
    }


    private boolean isButtonSignOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;

        }


    }

    @Test
    public void inValidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://qa-complexapp.onrender.com/");
        WebElement inputUserName =
                webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));

        inputUserName.clear();
        inputUserName.sendKeys("invalidLog");


        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");


        WebElement buttonSignIn =
                webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();

        //just for checking red alert
        WebElement redMessage =
                webDriver.findElement(By.xpath(".//*[@class = 'alert alert-danger text-center']"));
        redMessage.isDisplayed();


        Assert.assertTrue("Do u see red message ? - ok, test passes", isRedAlertDisplayed());


        webDriver.quit();
    }

    private boolean isRedAlertDisplayed() {
        try {
            return webDriver.findElement(By.xpath("//*[@class = 'alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return true;

        }


    }
}

