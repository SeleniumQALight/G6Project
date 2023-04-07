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

import java.util.ArrayList;
import java.util.List;



public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;
    @FindBy(xpath = ".//input[@name='username' and @id='username-register']")
    private WebElement inputLoginRegistration;
    @FindBy(xpath = ".//input[@name='email']")
    private WebElement inputEmailRegistration;
    @FindBy(xpath = ".//input[@name='password' and @id = 'password-register']")
    private WebElement inputPasswordRegistration;
    public static final String listOfErrorsLocators = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    final static private String alertDangerText = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = '%s']";
    @FindBy(xpath = listOfErrorsLocators)
    private List<WebElement> alertText;
    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement signInErrorText;
    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    private TestData testData = new TestData();

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
            logger.info(base_url);
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    @Step
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

    @Step
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

    @Step
    public void clickButtonLogin() {
       clickOnElement(buttonLogin);
        }

    @Step
    public HomePage fillingLoginFormWhitValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickButtonLogin();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterUserNameIntoInputRegistrationForm(String userName){
        enterTextInToElement(inputLoginRegistration, userName);
        return this;
    }

    @Step
    public LoginPage enterEmailIntoInputRegistrationForm(String email){
        enterTextInToElement(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoInputRegistrationForm(String password){
        enterTextInToElement(inputPasswordRegistration, password);
        return this;
    }

    @Step
    public LoginPage checkErrorMessages (String expectedErrors) {
        //error1, error2 ->array[0] = error1, array[1] = error2
        //підходить для перевірки блока чи таблиці як що треба перевірити данні
        String[] expectedErrorArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be" + expectedErrorArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocators), expectedErrorArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of message", expectedErrorArray.length, alertText.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : alertText) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorArray.length; i++) {
            softAssertions.assertThat(expectedErrorArray[i])
                    .as("Message is not equals")
                    .isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }

    @Step
    public LoginPage checkLoginErrorMessage(String expectedError) {
        Assert.assertTrue("Error Login/Password message is not displayed", isElementDisplayed(signInErrorText));
        Assert.assertEquals("Error message does not match. Expected: " + expectedError + " but Actual: "
                + signInErrorText.getText(), expectedError, signInErrorText.getText());
        return this;
    }

    @Step
    public LoginPage checkAlertMessageWithText(int numberOfErrors){
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocators),numberOfErrors));
        Assert.assertEquals("The message is not displayed",numberOfErrors, alertText.size());
        return this;
    }

    @Step
    public LoginPage checkSingInButtonIsDisplayed(){
        webDriverWait10.until(ExpectedConditions.visibilityOf(buttonLogin));
        Assert.assertTrue(buttonLogin + "Button is not displayed", isElementDisplayed(buttonLogin));
        return this;
    }

    @Step
    public LoginPage checkErrorMessageWithText(String alertMessage){
        Assert.assertTrue(alertMessage + "The message is not equal", isElementDisplayed(String.format(alertDangerText,alertMessage)));
        return this;
    }

    @Step
    public LoginPage userNameTabKey(int number,String userName){
        usersPressesKeyTabTime(number);
        userEnterText(userName);
        return this;
    }
    @Step
    public LoginPage passwordTabKey(int number, String passwordEnter){
        usersPressesKeyTabTime(number);
        userEnterText(passwordEnter);
        return this;
    }

    @Step
    public LoginPage registrationUserNameTabKey(int number,String userName){
        usersPressesKeyTabTime(number);
        userEnterText(userName);
        return this;
    }

    @Step
    public LoginPage registrationEmailTabKey(String email){
        usersPressesKeyTabTime(1);
        userEnterText(email);
        return this;
    }

    @Step
    public LoginPage registrationPasswordTabKey(String password){
        usersPressesKeyTabTime(1);
        userEnterText(password);
        return this;
    }

    public void checkAlertInCenter(String expectedText) {
        Assert.assertEquals("Message in alert", expectedText, alertInCenter.getText());
    }
}

