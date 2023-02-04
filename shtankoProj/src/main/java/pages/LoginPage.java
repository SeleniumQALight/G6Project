package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

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
        enterTextInToElement(inputUserName, userName);

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
        enterTextInToElement(inputPassword, password);
    }

    public void clickButtonLogin() {
        clickOnElement(buttonLogin);
    }

    public boolean alertLoginDanger() {
        try {
            return webDriver.findElement(By.xpath(".//a[@class='btn btn-sm btn-success mr-2']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    public HomePage fillingLoginFormWhitValidCred() {
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickButtonLogin();
        return new HomePage(webDriver);
    }
}

