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
    private WebDriver driver;

    @Test
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("Browser was opened");
        driver.get("https://qa-complexapp.onrender.com/");

        WebElement inputUsername = driver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys("qaauto");
        System.out.println("login was inputted");

        WebElement inputPassword = driver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("Password was entered");

        WebElement signIn = driver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        signIn.click();
        System.out.println("Button was clicked");

        Assert.assertTrue("Button is not displayed", isButtonSignOutDisplayed());


        driver.quit();
        System.out.println("Browser was closed");

    }

    @Test
    public void negativeLogin() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("Browser was opened");
        driver.get("https://qa-complexapp.onrender.com/");

        WebElement inputUsername = driver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys("qaauto");
        System.out.println("Login was inputted");

        WebElement inputPassword = driver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwert");
        System.out.println("Password was entered");

        WebElement signIn = driver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        signIn.click();
        System.out.println("Button was clicked");

        Assert.assertTrue("Invalid username pasword", isInvalidUserNameDisplayed());

        driver.quit();
        System.out.println("Browser was closed");


    }

    private boolean isButtonSignOutDisplayed() {
        try {
            return driver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;

        }
    }

    private boolean isInvalidUserNameDisplayed() {
        try {
            WebElement invalidUserName = driver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
            return invalidUserName.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
}
