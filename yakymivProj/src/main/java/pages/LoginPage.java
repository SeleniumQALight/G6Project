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
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPass;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement logInErrorMessage;

    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;

    private static final  String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

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
            logger.error("Can't open Login Page " + e);
            Assert.fail("Can't open Login Page " + e);
        }
        return new LoginPage(webDriver);
    }

    public void enterUserNameintoInputLogin(String userName) {
        enterTextToElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextToElement(inputPass, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonSignIn);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameintoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public LoginPage fillingLoginFormWithInvalidCred(String userName, String password) {
        openLoginPage();
        enterUserNameintoInputLogin(userName);
        enterPasswordIntoInputPassword(password);
        clickOnButtonLogin();
        return this;
    }

    public boolean isButtonSignInDisplayed() {
        return isButtonDisplayed(buttonSignIn);
    }

    public LoginPage enterUserNameintoRegistrationForm(String userName) {
        enterTextToElement(inputLoginRegistration, userName);
        return this;
    }

    public LoginPage enterEmailInRegistrationForm(String email){
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordInRegistrationForm(String pass){
        enterTextToElement(inputPasswordRegistration, pass);
        return this;
    }

    public LoginPage checkErrorMessage(String expectedErrors) {
        String[]expectedErrorArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be" + expectedErrorArray.length)
                .until(ExpectedConditions.
                        numberOfElementsToBe(By.xpath(listOfErrorsLocator),expectedErrorArray.length));

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors){
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorArray.length; i++) {
            softAssertions.assertThat(expectedErrorArray[i]).as("Message is not equals").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

        return this;
    }

    public void checkLogInErrorMessage(String expectedError) {
        Assert.assertEquals("Text in success message element: ", expectedError, logInErrorMessage.getText());
        Assert.assertFalse("Sign out button is displayed",  getHeaderElement().isButtonSignOutDisplayed());
    }
}
