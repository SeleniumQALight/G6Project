package pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement signInBtn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Login page was open");
        } catch (Exception e) {
            logger.error("Can't Open page " + e);
            Assert.fail("Can't Open page " + e);
        }
        return this;
    }

    public void typeUserName(String userName) {
        typeTextToElement(inputUserName, userName);
    }

    public void typeUserPassword(String password) {
   /*     try {
           // WebElement passwordInput = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            passwordInput.clear();
            passwordInput.sendKeys(password);
            logger.info("Type password ");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }*/
        typeTextToElement(passwordInput, password);
    }

    public HomePage clickSignIn() {
     /*   try {
          //  WebElement signInBtn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
            signInBtn.click();
            logger.info("Sign in clicked");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }*/
        click(signInBtn);
        return new HomePage(webDriver);
    }
}
