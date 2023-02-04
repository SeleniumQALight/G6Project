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
    public void validLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("browser was opened");
        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("Site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name = 'username' and @placeholder = 'Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("aqaTestComplexapp");
        System.out.println("login was inputted");

        WebElement inputUserPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputUserPassword.clear();
        inputUserPassword.sendKeys("Test87654321");
        System.out.println("Password was inputted");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");

        Assert.assertTrue("'Sign Out' button is not displayed", isButtonSignOutDisplayed());

        webDriver.quit();
        System.out.println("browser was closed");
    }

    @Test
    public void invalidLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("browser was opened");
        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("Site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name = 'username' and @placeholder = 'Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("aqaTestComplexapp");
        System.out.println("login for non-existing user was inputted");
        WebElement inputUserPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputUserPassword.clear();
        inputUserPassword.sendKeys("Failed87654321");
        System.out.println("Password was inputted");
        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Sign In button was clicked");

        Assert.assertFalse("'Sign Out' button is displayed", isButtonSignOutDisplayed());
        webDriver.quit();
        System.out.println("browser was closed");
    }

    private boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }
        catch (Exception e) {
            return false;
        }
    }
}
