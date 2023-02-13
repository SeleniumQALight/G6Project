package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;




//Первое задание дошнего задания №4
    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement userNameFieldForRegister;


    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement emailFieldForRegister;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement passwordFieldForRegister;


    private String textError="//div[text()='%s']";




    public LoginPage(WebDriver webDriver) {

        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Login page was open");
        } catch (Exception e) {
            logger.error("Can not open login page" + e);
            Assert.fail("Can not open login page" + e);
        }
    }


    public void enterUserNameIntoInputLogin(String userName) {

        enterTextIntiElement(inputUserName,userName);
    }


    public void enterPasswordIntoInputPassword(String password) {

        enterTextIntiElement(inputPassword,password);
    }

    public void clickOnButtonLogin() {

        clickOnElement(buttonLogin);
    }


    public HomePage fillingLoginFormWithValidCred() {
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }





    public LoginPage enterUserNameIntoInputUserNameForRegister(String userName, String text){
        enterTextIntiElement(userNameFieldForRegister, userName);
        WebDriverWait webDriverWait=new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement errorOnField=webDriver.findElement(By.xpath(String.format(textError,text)));
        webDriverWait.until(ExpectedConditions.visibilityOf(errorOnField));
        Assert.assertTrue("The userName error text is not displayed.",errorOnField.isDisplayed());
        return this;
    }

    public LoginPage enterEmailIntoInputEmailForRegister(String email, String text){
        enterTextIntiElement(emailFieldForRegister, email);
        WebDriverWait webDriverWait=new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement errorOnField=webDriver.findElement(By.xpath(String.format(textError,text)));
        webDriverWait.until(ExpectedConditions.visibilityOf(errorOnField));
        Assert.assertTrue("The email error text is not displayed.",errorOnField.isDisplayed());
        return this;
    }

    public LoginPage enterPasswordIntoInputPasswordForRegister(String password, String text){
        enterTextIntiElement(passwordFieldForRegister, password);
        WebDriverWait webDriverWait=new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement errorOnField=webDriver.findElement(By.xpath(String.format(textError,text)));
        webDriverWait.until(ExpectedConditions.visibilityOf(errorOnField));
        Assert.assertTrue("The password error text is not displayed.",errorOnField.isDisplayed());
        return this;
    }









}

