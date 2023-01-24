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
        System.out.println( "Browser is opened" );
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait( 14, TimeUnit.SECONDS );
        webDriver.get( "https://qa-complexapp.onrender.com/" );
        WebElement userNameInput =
                webDriver.findElement( By.xpath( ".//*[@name='username' and @placeholder='Username']" ) );
        userNameInput.click();
        userNameInput.sendKeys( "qaauto" );

        WebElement userPswInput =
                webDriver.findElement( By.xpath( ".//*[@name='password' and @placeholder='Password']" ) );
        userPswInput.click();
        userPswInput.sendKeys( "123456qwert1y" );

        WebElement signIn = webDriver.findElement( By.xpath( ".//*[@class='btn btn-primary btn-sm']" ) );
        signIn.click();

        WebElement signOutBtn = webDriver.findElement( By.xpath( ".//button[text()='Sign Out']" ) );
        Assert.assertTrue( "Button is not visible ", isElementDisplayed( signOutBtn ) );
        webDriver.quit();

    }

    @Test
    public void invalidLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("Browser was opened");
        webDriver.get("https://qa-complexapp.onrender.com/");

        WebElement inputUsername = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys("mutest");
        System.out.println("login was inputted");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("qqq");
        System.out.println("Password was entered");

        WebElement signIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        signIn.click();
        System.out.println("Button was clicked");
        WebElement errorMessage = webDriver.findElement( By.xpath( ".//div[@class='alert alert-danger text-center']" ) );

        Assert.assertTrue("Button is not displayed", isElementDisplayed( errorMessage ));


        webDriver.quit();
        System.out.println("Browser was closed");

    }

    private boolean isElementDisplayed( WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;

        }
}
}
