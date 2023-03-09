package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    public static final String ALERT_XPATH = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private final String parametrizedAlert = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    @FindBy(xpath = ALERT_XPATH)
    private List<WebElement> listOfErrors;

    @FindBy(xpath = ".//*[@class='alert alert-danger text-center' and text()='Invalid username  pasword']")
    private WebElement signInError;

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

    @Override
    String getRelativeURL() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
            logger.info(base_url);
        } catch (Exception e) {
            logger.error("Cannot open Login Page" + e);
            Assert.fail("Cannot open Login Page" + e);

        }
        return this;
    }

    @Step
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

    @Step
    public void enterPasswordIntoInputPassword(String password) {

        enterTextIntoElement(inputPassword, password);
    }

    @Step
    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    @Step
    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    @Step
    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterUserNameIntoInputSignUp(String userName) {
        enterTextIntoElement(inputUserNameSignUp, userName);
        return this;
    }

    @Step
    public LoginPage enterEmailIntoInputSignUp(String email) {
        enterTextIntoElement(inputEmailSignUp, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoInputSignUp(String password) {
        enterTextIntoElement(inputPasswordSignUp, password);
        return this;
    }

    @Step
    public LoginPage checkThreeAlertMassagesAreDisplayed() {
        WebDriverWait wdw = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wdw.until(ExpectedConditions.numberOfElementsToBe(By.xpath(ALERT_XPATH), 3));
        Assert.assertEquals("Validation doesn't work", 3, alertMessages.size());
        return this;
    }

    @Step
    public LoginPage checkErrorMessageWithText(String textMessage) {
        Assert.assertTrue("Text \"" + textMessage + "\" not found", isElementDisplayed(String.format(parametrizedAlert, textMessage)));
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        // error1,error2,error3 -> array[0] = error1, array[1] = error2, array[2] = error3
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be" + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(ALERT_XPATH), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i])
                    .as("Message is not equals")
                    .isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

        return this;
    }

    @Step
    public void checkErrorMessageForLogin(String expectedErrorLogin) {
        Assert.assertTrue("Error message in not displayed when login failed", isElementDisplayed(signInError));
    }

    @Step
    public LoginPage loginFromKeyboard(String login, String password) {
        usersPressesKeyTabTime(2);
        inputFromKeyboard(login);
        usersPressesKeyTabTime(1);
        inputFromKeyboard(password);
        usersPressesKeyEnterTime(1);
        return this;
    }

    @Step
    public LoginPage registrationFromKeyBoard(String username, String email, String password) {
        usersPressesKeyTabTime(5);
        inputFromKeyboard(username);
        usersPressesKeyTabTime(1);
        inputFromKeyboard(email);
        usersPressesKeyTabTime(1);
        inputFromKeyboard(password);
        return this;
    }

    @Step
    public LoginPage switchToPreviousTabAndRefresh() {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0));
        webDriver.navigate().refresh();
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

