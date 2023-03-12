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

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(xpath = ".//*[@class='alert alert-danger text-center']")
    private WebElement loginAlertMessage;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfAlertMessages;

    private String actualAlertMessage = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage(){
        try {
            webDriver.get(baseURL + getRelativeURL());
            logger.info("Login page was opened");
            logger.info(baseURL);
        } catch (Exception e){
            logger.error("Can't open login page" +e);
            Assert.fail("Can't open login page" +e);
        }
        return this;
    }

    @Step
    public LoginPage enterUserNameIntoInputLogin(String userName) {

        enterTextIntoElement(inputUserName, userName);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoInputPassword(String password)
    {
        enterTextIntoElement(inputPassword, password);
        return this;
    }

    @Step
    public LoginPage clickOnButtonLogin() {
        clickOnElement(buttonLogin);
        return this;
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();

        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterUserNameIntoRegistrationInput(String userName) {
        enterTextIntoElement(inputUserNameRegistration, userName);
        return this;
    }

    @Step
    public LoginPage enterEmailIntoRegistrationInput(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoRegistrationInput(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage checkTextInAlertMessage(String expectedAlertTitle) {
        Assert.assertTrue("Alert message does not correspond", isElementDisplayed(String.format(actualAlertMessage, expectedAlertTitle)));
        return this;
    }


    public LoginPage checkNumberOfAlertMessages() {
        webDriverWait15.until(ExpectedConditions.numberOfElementsToBe(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"), 3));
        Assert.assertEquals("The number of messages is not equal to 3", 3, listOfAlertMessages.size());
        return this;
    }

    public LoginPage checkErrorMessages(String expectedErrors) {
        //error1, error2 > array[0]
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be" + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrorsArray.length, listOfAlertMessages.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfAlertMessages) {
            actualTextFromErrors.add(element.getText());

        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Messages are not equal").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();


        return this;
    }

    public LoginPage checkErrorMessagesOnLogin(String expectedErrors) {
        Assert.assertTrue("Alert message is not shown", isElementDisplayed(loginAlertMessage));
        Assert.assertEquals(expectedErrors, loginAlertMessage.getText());
        return this;
    }


    // private List<WebElement>


//    public LoginPage enterUserNameInRegistrationForm(String userName) {
//        enterTextIntoElement(inputUserNameRegistration, userName);
//        return this;
//    }
}
