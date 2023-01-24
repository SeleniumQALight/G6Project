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


        WebElement signOutbtn = webDriver.findElement( By.xpath( ".//button[text()='Sign Out']" ) );

        Assert.assertTrue( "dwd", isButtonSingOutnDisplayed() );
        webDriver.quit();

    }


    private boolean isButtonSingOutnDisplayed() {
        try {
            return webDriver.findElement( By.xpath( ".//button[text()='Sign Out']" ) ).isDisplayed();
        } catch ( Exception e ) {
            return false;
        }
    }
}
