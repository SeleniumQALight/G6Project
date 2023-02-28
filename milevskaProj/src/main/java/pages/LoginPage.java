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
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginRegistration;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailRegistration;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    private String assertions = "//label[@for = '%s']/../div";
    private String listOfAssertion = ".//*[text()='%s']";

    private static final String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeURL() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String username) {
        //try {
        //WebElement inputUsername = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        // inputUserName.clear();
        // inputUserName.sendKeys(username);
        // logger.info("login was input");
        //}catch (Exception e){
        //    printErrorAndStopTest(e);
        //}
        enterTextInToElement(inputUserName, username);
    }

    public void enterPasswordIntoInputPassword(String password) {
        try {
            //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            inputPassword.clear();
            inputPassword.sendKeys(password);
            logger.info("Password was entered");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
        enterTextInToElement(inputPassword, password);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public LoginPage enterUsernameInRegistrationForm(String username) {
        enterTextInToElement(inputLoginRegistration, username);
        return this;
    }

    public LoginPage enterEmailInRegistrationForm(String email) {
        enterTextInToElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPassInRegistrationForm(String password) {
        enterTextInToElement(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage checkIsAssertionDisplayed(String assertName, String expectedUsernameAssertion){
        WebElement expectedLocator = webDriver.findElement(By.xpath(String.format(assertions, assertName)));
        webDriverWait10.until(ExpectedConditions.visibilityOf(expectedLocator));
        Assert.assertEquals("Assert username message is not displayed", expectedUsernameAssertion, expectedLocator.getText());
        return this;
    }

    public LoginPage checkIsAllAssertionDisplayed(int countOfAssertions) {
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator),countOfAssertions));
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equals ").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }
}



