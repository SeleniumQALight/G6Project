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
    WebDriver webDriver;  //object who save browser for tests

    @Test
    public void validLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();  //browser opening (in small window)
        webDriver.manage().window().maximize(); //open browser window on maximum
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  //waiting delay e.g. if cannot press a button immedialety
        System.out.println("browser was opened");

        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("site was opened");
        WebElement inputUserName =
                webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        System.out.println("login was inputted");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty1");
        System.out.println("password was inputted");

        WebElement buttonSignIn =
                webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");

//        WebElement buttonSignOut =
//                webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));

        Assert.assertTrue("Button is not displayed", isButtonSignOutDisplayed());


        webDriver.quit(); //browser closing
        System.out.println("browser was closed");

    }

    private boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

}
