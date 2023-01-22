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
        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("site was opened");
        System.out.println("opened");
        WebElement inputUsername = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys("qaauto");
        System.out.println("login was input");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty1");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");


        Assert.assertTrue("Button is not displayed", isButtonSignOutIsDisplayed());

         webDriver.quit();
        System.out.println("closed");
    }
     private boolean isButtonSignOutIsDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
     }catch (Exception e){
            return false;
        }
    }
}
