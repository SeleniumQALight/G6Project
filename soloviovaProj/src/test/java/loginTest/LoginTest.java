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
    // object that will keep browser that understands java commands
    private WebDriver webDriver;

    // use @Test instead of Main method. Each @Test with method is an independent testcase.
    @Test
    public void validLogin() {
        // this line downloads/set up necessary version on driver for chrome.
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(); // assigns particular browser to WebDriver object. + it actually opens browser.
        webDriver.manage().window().maximize();// this maximizes window of browser itself.
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // this method defines the duration in which our method attempts to perform the command
        System.out.println("Browser was opened.");

        // this method opens website
        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("Website was opened.");
        //capture an input to interact with.
        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        System.out.println("user name was entered.");
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@name='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("Password was entered.");
        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked.");

       // WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        //check if log in has been successful using method that catch an exception.
        Assert.assertTrue("Button is not displayed", IsButtonSignOutDisplayed());
        System.out.println("Sign out button is displayed");

        webDriver.quit(); // this line closes browser, .close() closes only window of used app.
        System.out.println("Browser was closed");
    }
    //catch un exception if element was not found
    private boolean IsButtonSignOutDisplayed(){
        try {
           return  webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

}
