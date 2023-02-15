package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;
    @FindBy(xpath = ".//input[@name='username' and @id='username-register']")
    private WebElement inputUserNameRegistered;
    @FindBy(xpath = ".//input[@name='email']")
    private WebElement inputEmail;
    @FindBy(xpath = ".//input[@name='password' and @id = 'password-register']")
    private WebElement inputPasswordRegistered;
    public static final String alertDanger = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    private String alertDangerText = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = '%s']";
    @FindBy(xpath = alertDanger)
    private List<WebElement> alertText;
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    private TestData testData = new TestData();

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
      // try {
      //      WebElement inputUserName = webDriver.findElement(
      //              By.xpath(".//input[@name='username' and @placeholder='Username']"));
       //     inputUserName.clear();
       //     inputUserName.sendKeys(userName);
     //       logger.info("login was inputted");
     //   } catch (Exception e){
     //       printErrorAndStopTest(e);
     //   }
            enterTextInToElement(inputUserName,userName);

    }

    public void enterPasswordIntoInputPassword(String password) {
      //  try {
           // WebElement inputPassword =
             //       webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
   //         inputPassword.clear();
  //          inputPassword.sendKeys(password);
  //          logger.info("Password was entered");
  //      }catch (Exception e){
      //      printErrorAndStopTest(e);
      //  }
        enterTextInToElement(inputPassword,password);
    }

    public void clickButtonLogin() {
       clickOnElement(buttonLogin);
        }

    public HomePage fillingLoginFormWhitValidCred() {
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickButtonLogin();
        return new HomePage(webDriver);
    }
    public void enterUserNameIntoInputRegisteredForm(String userNameRegistered){
        enterTextInToElement(inputUserNameRegistered, userNameRegistered);
    }
    public void enterEmailIntoInputRegistered(String email){
        enterTextInToElement(inputEmail, email);
    }
    public void enterPasswordIntoInputRegisteredForm(String passwordRegistered){
        enterTextInToElement(inputPasswordRegistered,passwordRegistered);
    }

    public LoginPage checkAlertMessageWithText(){
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(alertDanger),3));
        Assert.assertEquals("The message is not displayed",3, alertText.size());
        return this;
    }
    public LoginPage checkErrorMessageWithText(String alertMessage){
        Assert.assertTrue(alertMessage + "The message is not equal", isElementDisplayedAlert(String.format(alertDangerText,alertMessage)));
        return this;
    }
}

