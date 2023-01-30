package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest { // класс це міні сют тестів по функціональності LoginTest
    private WebDriver webDriver; // потрібно заімпортувати веб драйвер(браузер) org.openqa.selenium. Це обєкт в якому буде зберігатися браузер який буде розуміти команди з Java.  Коли ми обявляємо саму змінну webDriwer це інтерфейс і внього команди які будуть однакові для будь якого драйвера. WebDriver це команди які можуть бути виконані в нашому браузері

    @Test // анотація повідомляє що за нею буде слідувати метод в якому буде наш тест кейс і який може бути запущений
    public void validLogin(){
        WebDriverManager.chromedriver().setup(); // команда яка завантажує файл останньої версії браузера і зберігає його в папці М2
        webDriver = new ChromeDriver(); // команда new ChromeDriver(); це відкриття браузера візуально. Оголосили змінну в якій буде зберігатися наш драйвер.ChromeDriver це реалізація цих команд під конкретний браузер
        webDriver.manage().window().maximize(); // команда яка розкриває браузер на весь екран
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //налаштування дефолтного очікування Java на відкриття елемента. Напротязі заданого часу веб драйвер намагається виконати дію
        System.out.println("browser was opened");
        webDriver.get("https://qa-complexapp.onrender.com/"); // команда відкриття сайту
        System.out.println("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")); // створили змінну назвали inputUserName тип її WebElement(підтягнувся з бібліотеки) і кажемо webDriver знайди елемент findElement використовуючи By.xpath і якщо елемент знайде запише в змінну inputUserName
        inputUserName.clear(); //  тепер можемо спілкуватися не з webDriver а з inputUserName і наприклад треба почистити поле
        inputUserName.sendKeys("qaauto"); //команда ввести логін
        System.out.println("login was inputted");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        System.out.println("password was inputted");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");

//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']")); //

        Assert.assertTrue("Button is not displayed", isButtonSighOutDisplayed()); // мати можливість робити перевірку. За перевірку у нас відповідає частина джеюніта і ми використовуємо Assert і в нього є різні методи. Assert це перевірки. Умова тест зелений коли кнопка є. Повідомлення виводиться коли тест faild


        webDriver.quit(); // команда яка закриває браузер
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
        System.out.println("site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        System.out.println("login was inputted");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("X123456qwerty");
        System.out.println("password was inputted");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("button was clicked");


        Assert.assertTrue("Button SighIn is not displayed", isButtonSignInDisplayed());

        webDriver.quit();
        System.out.println("browser was closed");
    }


    private boolean isButtonSighOutDisplayed(){ // Методи які повертають якийсь стан буть кого чи чого якщо ми кажемо сходи перевір і поверни true якщо щось або false якщо щось завжди будуть починатися з слова is
       try {
           return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed(); // Якщо ти виконав цю дію і знайшов елемент запитай чи показаний він .isDisplayed(); і коли отримаєш відповідь то поверни її зовні return. Якщо показаний тоді повернеться true якщо ні тоді false
       }catch (Exception e){ //перехоплюємо будь які Exception
           return false;// повертаємо що елемента просто немає
       }
    }

    private boolean isButtonSignInDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }


}
