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

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement signInButton;

    @FindBy(id = "username-register")
    private WebElement usernameInputRegistrationField;

    @FindBy(id = "email-register")
    private WebElement emailInputRegistrationField;

    @FindBy(id = "password-register")
    private WebElement passwordRegistrationInputField;

    private String errorMessageLocator = ".//*[text()='%s']";

    private static final String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

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

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }


    public boolean isSignInButtonDisplayed() {
        return isElementPresented(signInButton);
    }


    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public LoginPage enterUserNameToTheRegistrationField(String username) {
        enterTextInToElement(usernameInputRegistrationField, username);
        return this;
    }

    public LoginPage enterEmailToTheRegistrationField(String email) {
        enterTextInToElement(emailInputRegistrationField, email);
        return this;
    }

    public LoginPage enterPasswordToTheRegistrationField(String password) {
        enterTextInToElement(passwordRegistrationInputField, password);
        return this;
    }

    public boolean checkErrorMessageWithText(String enterTextError) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(errorMessageLocator, enterTextError));

        return true;
    }



    public LoginPage checkErrorMessages(String expectedErrors) {
        //error1,error2, -> array[0] = error1, array[1] = error2
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator),
                        expectedErrorsArray.length));

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i])
                    .as("Message is not equals").isIn(actualTextFromErrors);


        }
        softAssertions.assertAll();
        return this;
    }


}