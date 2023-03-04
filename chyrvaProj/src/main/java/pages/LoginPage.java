package pages;

import libs.TestData;
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

    final static String ERROR_MESSAGE_LOGIN = "Invalid username pasword";

    private static final String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;


    private String errorMessage = "//*[@class = \"alert alert-danger small liveValidateMessage liveValidateMessage--visible\" and text() = '%s' ]";
    private String errorMessageForLogin = "//*[@class='alert alert-danger text-center']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
            logger.info(base_url);
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
    public LoginPage checkErrorMessageWithTextForLogIn() {

        Assert.assertTrue("Error Message is not displayed",
                isElementDisplayed(String.format(errorMessageForLogin, ERROR_MESSAGE_LOGIN)));


        return this;
    }

    public LoginPage checkErrorMessagesIsDisplayed(int expectedSize) {
        Assert.assertEquals("Number of messages is not three"
                , expectedSize, getMessagesList().size());

        return this;
    }

    public List<WebElement> getMessagesList() {
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorMessagesSignUpForm), 3));

        return webDriver.findElements(
                By.xpath((errorMessagesSignUpForm)));


    }

    public LoginPage enterUserNameInRegistrationForm(String userName) {
        enterTextInToElement(inputLoginRegistration, userName);
        return this;
    }

    public LoginPage enterEmailInRegistrationForm(String email) {
        enterTextInToElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordInRegistrationForm(String password) {
        enterTextInToElement(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage checkErrorMessages(String expectedErrors) {
        //error1,error2 -> array[0] = error1 , array[1] = error2
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i])
                    .as("Message is not equals ")
                    .isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

        return this;
    }

}