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
    WebDriver webDriver;

    @Test
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("browser was opened");
        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("site was opened");

        WebElement inputUsername = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys("qaauto");
        System.out.println("login was entered");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was entered");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("button was clicked");

        // WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        Assert.assertTrue("Button isn't displayed", isButtonSignOutDisplayed());

        webDriver.quit();
        System.out.println("browser was closed");

    }

    @Test
    public void invalidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("browser was opened");
        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("site was opened");

        WebElement inputUsername = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys("qaautoInvalidName");
        System.out.println("login was entered");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("12345qwerty");
        System.out.println("password was entered");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("button was clicked");

        Assert.assertTrue("Alert isn't displayed", isAlertInvalidUserNameOrPasswordDisplayed());
        Assert.assertTrue("Login form isn't displayed", isFormLoginDisplayed());

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

    private boolean isFormLoginDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//form[@action='/login']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isAlertInvalidUserNameOrPasswordDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
