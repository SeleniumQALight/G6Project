package pages;

import library.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage extends ParentPage {
    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement errorMessages;
    private String errorMessagesSignUpForm = ".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;
    @FindBy(xpath = "//input[@placeholder= 'Pick a username']")
    private WebElement pickAUsername;
    @FindBy(xpath = "//input[@placeholder= 'you@example.com']")
    private WebElement yourExampleEmail;
    @FindBy(xpath = "//input[@placeholder= 'Create a password']")
    private WebElement createPassword;


    private String errorMessage = "//*[@class = \"alert alert-danger small liveValidateMessage liveValidateMessage--visible\" and text() = '%s' ]";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);

        }
        return this;
    }

    public void enterUserNameIntoInputLogin(String username) {
        // try {
        //  WebElement inputUserName =
        //         webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        //  inputUserName.clear();
        // inputUserName.sendKeys(username);
        //  logger.info("login was inputted");

        //  } catch (Exception e) {
        //      printErrorAndStopTest(e);

        // }
        enterTextInToElement(inputUserName, username);
    }

    public void enterPasswordIntoInputPassword(String password) {
        // try {
        //  WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("Password was entered");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
//        try {
//            //  WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
//            buttonLogin.click();
//            logger.info("Button was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonLogin);
    }

    public boolean isButtonLogInDisplayed() {
//        try {
//            return webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
//        } catch (Exception e) {
//            return false;
//
//        }
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithValidCred() {
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();

        return new HomePage(webDriver);
    }

    public LoginPage enterTextInPickAUsername(String username) {
        enterTextInToElement(pickAUsername, username);
        return this;
    }

    public LoginPage enterTextInYourEmailExpample(String email) {
        enterTextInToElement(yourExampleEmail, email);
        return this;
    }

    public LoginPage enterTextInCreatePass(String password) {
        enterTextInToElement(createPassword, password);
        return this;
    }

    public LoginPage checkErrorMessageWithText(String textMessage) {

        Assert.assertTrue("Error Message is not displayed",
                isElementDisplayed(String.format(errorMessage, textMessage)));


        return this;
    }


    public LoginPage checkThreeErrorMessagesIsDisplayed(){
        Assert.assertEquals("Number of messages is not three"
                , 3, getMessagesList().size());

        return this;
    }

    public List<WebElement> getMessagesList(){
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorMessagesSignUpForm),3));

        return webDriver.findElements(
                By.xpath((errorMessagesSignUpForm)));





    }

}