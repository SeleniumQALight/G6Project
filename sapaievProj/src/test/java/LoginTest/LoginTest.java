package LoginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginTest {


    private WebDriver webDriver;


    @Test
    public void validLogin() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(ops);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("browser was open");

        webDriver.get("https://aqa-complexapp.onrender.com/");
        System.out.println("site was open");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("Kamal");


        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@type='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("Test12345678");

        WebElement LoginButton = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        LoginButton.click();

        //WebElement SignOutButton=webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));

        Assert.assertTrue("Button is not Displayed", isButtonSignOutDisplayed());

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
    public void invalidLogin(){
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(ops);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://aqa-complexapp.onrender.com/");

        WebElement inputUserName=webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("Kamal");

        WebElement inputPassword=webDriver.findElement(By.xpath("//input[@type='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("Test1234567818188");

        WebElement LoginButton=webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        LoginButton.click();

        Assert.assertFalse("Test success is failed",isButtonSignOutDisplayed());
        webDriver.quit();
        System.out.println("browser was closed");

    }

    @Test
    public void invalidLoginWithEmptyField(){
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(ops);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://aqa-complexapp.onrender.com/");

        WebElement LoginButton=webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        LoginButton.click();

        Assert.assertFalse("Test success is failed",isButtonSignOutDisplayed());
        webDriver.quit();
        System.out.println("browser was closed");

    }


    @Test
    public void invalidLoginWithCapitalizeLetter(){
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(ops);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://aqa-complexapp.onrender.com/");

        WebElement inputUserName=webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("Kamal");

        WebElement inputPassword=webDriver.findElement(By.xpath("//input[@type='password' and @placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("TEST12345678");

        WebElement LoginButton=webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        LoginButton.click();

        Assert.assertFalse("Test success is failed",isButtonSignOutDisplayed());
        webDriver.quit();
        System.out.println("browser was closed");

    }


}
