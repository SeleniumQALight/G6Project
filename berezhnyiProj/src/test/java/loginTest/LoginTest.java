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
        System.out.println("Browser was opened");

        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("Website was opened");

        //тип weelement і variable inputUserName;
        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        System.out.println("Login was inputted");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("Password was inputted");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Butoon was clicked");

       // WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        Assert.assertTrue("Button is not present", isButtonSignOutDisplayed());


        webDriver.quit();
        System.out.println("Browser was closed");
    }

    private boolean isButtonSignOutDisplayed(){
        try {
           return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

}
