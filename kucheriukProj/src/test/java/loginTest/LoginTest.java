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

    @Test// Наши тесткейсы
    public void validLogin() {
        WebDriverManager.chromedriver().setup();//открывает браузер в данном случае хром
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();//открывает браузер на весь экран
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//говорим подождать 5 сек , чтобы сайт успел подгрузится
        System.out.println("browser was opened");
        webDriver.get("https://qa-complexapp.onrender.com/");//даем комманду зайти на сайт
        System.out.println("Site was opened");

        WebElement inputUserName =
                webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        //создали  переменную в которой указан адрес локатора(создали переменную чтобы каждый раз когда ее вызывали он бегал по этому локатору

        inputUserName.clear();//очисти поле ввода
        inputUserName.sendKeys("qaauto");//вели логин
        System.out.println("login was inputted");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));//нашли поле ввода пароля
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");//вели пароль
        System.out.println("password was inputted");

        WebElement buttonSignIn =
                webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));//нашли кнопку 'SignIn'
        buttonSignIn.click();//нажали на нее
        System.out.println("Button was clicked");
        WebElement buttonSignOut =
                webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));//нащли кнопку'Sign Out

        Assert.assertTrue("Button is not displayed", isButtonSignOutDisplayed());//проверяем есть ли эта кнопка

        webDriver.quit();//Закрывает браузер
        System.out.println("browser was closed");

    }

    private  boolean isButtonSignOutDisplayed() {
        try {
           return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
