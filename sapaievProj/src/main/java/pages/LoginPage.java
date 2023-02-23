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
import java.util.List;


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


    private String textError = "//div[text()='%s']";

    private String errorsForRegistr = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";



    public LoginPage(WebDriver webDriver) {

        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(base_URL + getRelativeURL());
            logger.info("Login page was open");
            logger.info(base_URL);
        } catch (Exception e) {
            logger.error("Can not open login page" + e);
            Assert.fail("Can not open login page" + e);
        }
    }


    public void enterUserNameIntoInputLogin(String userName) {

        enterTextIntiElement(inputUserName, userName);
    }


    public void enterPasswordIntoInputPassword(String password) {

        enterTextIntiElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {

        clickOnElement(buttonLogin);
    }


    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }


   public List<WebElement> getErrorList(String title) {
        return webDriver.findElements(By.xpath(title));
    }

    public LoginPage checkSizeOfErrorsList(int size) {
        webDriverWait15.until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorsForRegistr),size));
        Assert.assertTrue("The number of errors is"+size, getErrorList(errorsForRegistr).size() == size);
        return this;
    }


    public LoginPage enterUserNameIntoInputUserNameForRegister(String userName) {
        enterTextIntiElement(userNameFieldForRegister, userName);
        return this;
    }

    public LoginPage checkUserNameError(String text) {
        isElementDisplayed(String.format(textError, text));
        WebElement error = webDriver.findElement(By.xpath(String.format(textError, text)));
        WebDriverWait wait10 = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait10.until(ExpectedConditions.visibilityOf(error));
        Assert.assertTrue("The userName error text is not displayed.", isElementDisplayed(String.format(textError, text)));
        return this;
    }


    public LoginPage enterEmailIntoInputEmailForRegister(String email) {
        enterTextIntiElement(emailFieldForRegister, email);
        return this;
    }

    public LoginPage checkEmailError(String text) {
        isElementDisplayed(String.format(textError, text));
        WebElement error = webDriver.findElement(By.xpath(String.format(textError, text)));
        WebDriverWait wait10 = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait10.until(ExpectedConditions.visibilityOf(error));
        Assert.assertTrue("The email error text is not displayed.", isElementDisplayed(String.format(textError, text)));
        return this;
    }

    public LoginPage enterPasswordIntoInputPasswordForRegister(String password) {
        enterTextIntiElement(passwordFieldForRegister, password);
        return this;
    }

    public LoginPage checkPasswordError(String text) {
        isElementDisplayed(String.format(textError, text));
        WebElement error = webDriver.findElement(By.xpath(String.format(textError, text)));
        WebDriverWait wait10 = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait10.until(ExpectedConditions.visibilityOf(error));
        Assert.assertTrue("The password error text is not displayed.", isElementDisplayed(String.format(textError, text)));
        return this;
    }


}

