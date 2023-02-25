package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")
    private WebElement inputRegistrationUserName;
    @FindBy(id = "email-register")
    private WebElement inputRegistrationEmail;
    @FindBy(id = "password-register")
    private WebElement inputRegistrationPassword;
    @FindBy(xpath = ".//button[@class = 'py-3 mt-4 btn btn-lg btn-success btn-block']")
    private WebElement buttonSignUp;

    @FindBy(xpath = registrationValidationMessages)
    private List<WebElement> listOfErrors;

    private String validationMessage = ".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = '%s']";
    private static final String registrationValidationMessages = ".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public LoginPage openLoginPage(){
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

    public LoginPage enterUserNameIntoInputLogin(String userName) {
        enterTextIntoElement(inputUserName, userName);
        return this;
    }

    public LoginPage enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPassword, password);
        return this;
    }

    public LoginPage clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
        return this;
    }

    public LoginPage enterUserNameIntoInputRegistrationUserName(String userName) {
        enterTextIntoElement(inputRegistrationUserName, userName);
        return this;
    }

    public LoginPage enterEmailIntoInputRegistrationEmail(String email) {
        enterTextIntoElement(inputRegistrationEmail, email);
        return this;
    }

    public LoginPage enterPasswordIntoInputRegistrationPassword(String password) {
        enterTextIntoElement(inputRegistrationPassword, password);
        return this;
    }

    public LoginPage checkIsValidationMessageDisplayed(String expectMessage){
        Assert.assertTrue("Alert message is not displayed", isElementDisplayed(String.format(validationMessage, expectMessage)));
        return this;
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSignIn);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();

        return new HomePage(webDriver);
    }

    public List<WebElement> getValidationMessagesList(){
        webDriverWait10.until(ExpectedConditions.attributeContains(By.xpath(registrationValidationMessages), "class", "liveValidateMessage--visible"));
        return  webDriver.findElements(By.xpath(registrationValidationMessages));
    }

    public LoginPage checkValidationMessagesAmount() {
        List<WebElement> listOfValidationMessages = getValidationMessagesList();
        Assert.assertEquals("Number of Alerts",3, listOfValidationMessages.size());
        return this;
    }

    public HomePage checkIsUserLoggedIn(){
        Assert.assertTrue("User is not logged in", headerElement.isButtonSignOutDisplayed());
        return new HomePage(webDriver);
    }

    public LoginPage checkIsUserNotLoggedIn(){
        Assert.assertFalse("Button is displayed", headerElement.isButtonSignOutDisplayed());
        Assert.assertTrue("User is logged in", isButtonSignInDisplayed());
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10.withMessage("Number of messages should be " + expectedErrorsArray.length).until(ExpectedConditions.numberOfElementsToBe(By.xpath(registrationValidationMessages), expectedErrorsArray.length));

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors){
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++){
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equals ").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

        return this;
    }
}
