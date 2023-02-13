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
    public static final String ALERT_XPATH = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private final String parametrizedAlert = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(xpath = ".//input[@name='username' and @id='username-register']")
    private WebElement inputUserNameSignUp;

    @FindBy(xpath = ".//input[@name='email' and @id='email-register']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = ".//input[@name='password' and @id='password-register']")
    private WebElement inputPasswordSignUp;

    @FindBy(xpath = ALERT_XPATH)
    private List<WebElement> alertMessages;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Cannot open Login Page" + e);
            Assert.fail("Cannot open Login Page" + e);

        }
        return this;
    }

    public void enterUserNameIntoInputLogin(String userName) {
//        try {
////            WebElement inputUserName =
////                    webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
//            inputUserName.clear();
//            inputUserName.sendKeys(userName);
//            logger.info("login was inputted");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//
//        }
        enterTextIntoElement(inputUserName, userName);

    }

    public void enterPasswordIntoInputPassword(String password) {

        enterTextIntoElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithValidCred() {
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public LoginPage enterUserNameIntoInputSignUp(String userName) {
        enterTextIntoElement(inputUserNameSignUp, userName);
        return this;
    }

    public LoginPage enterEmailIntoInputSignUp(String email) {
        enterTextIntoElement(inputEmailSignUp, email);
        return this;
    }

    public LoginPage enterPasswordIntoInputSignUp(String password) {
        enterTextIntoElement(inputPasswordSignUp, password);
        return this;
    }

    public LoginPage checkThreeAlertMassagesAreDisplayed() {
        WebDriverWait wdw = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wdw.until(ExpectedConditions.numberOfElementsToBe(By.xpath(ALERT_XPATH), 3));
        Assert.assertEquals("Validation doesn't work", 3, alertMessages.size());
        return this;
    }

    public LoginPage checkErrorMessageWithText(String textMessage) {
        Assert.assertTrue("Text \"" + textMessage + "\" not found", isElementDisplayed(String.format(parametrizedAlert, textMessage)));
        return this;
    }

//    public void checkErrorMessageWithText(String expected) {
//        boolean found = false;
//
//        for (int i = 0; i < alertMessages.size(); i++) {
//            String text = alertMessages.get(i).getText();
//            if (text.equals(expected)) {
//                found = true;
//                break;
//            }
//        }
//
//        Assert.assertTrue("Text \"" + expected + "\" not found", found);
//    }


}

