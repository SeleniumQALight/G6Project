package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    private String validationMessage = ".//*[text() = '%s']";
    private String registrationValidationMessages = ".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage(){
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
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

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
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
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();

        return new HomePage(webDriver);
    }

    public List<WebElement> getValidationMesagesList(){
        webDriverWait10.until(ExpectedConditions.attributeContains(By.xpath(registrationValidationMessages), "class", "liveValidateMessage--visible"));
        return  webDriver.findElements(By.xpath(registrationValidationMessages));
    }

    public LoginPage checkValidationMessagesAmount() {
        List<WebElement> listOfValidationMessages = getValidationMesagesList();
        Assert.assertEquals("Number of Alerts",3, listOfValidationMessages.size());
        return this;
    }
}
