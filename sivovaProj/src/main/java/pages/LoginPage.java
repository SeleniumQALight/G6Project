package pages;


import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@name='password' and @placeholder='Password']")
    private WebElement inputPassword;

    @FindBy (xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy (xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement errorMessage;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement userNameInputSignUp;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement emailInput;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement passwordInputSignUp;

    @FindBy(xpath = ".//button[@class='py-3 mt-4 btn btn-lg btn-success btn-block']")
    private WebElement buttonSignUp;

    private String errorMessageSignUp = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

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

    public LoginPage openLoginPage(){
        try{
            webDriver.get(base_url + getRelativeURL());
            logger.info("Login page was opened");
            logger.info(base_url);
            return this;

        } catch (Exception e) {
            logger.error("Can not open Login page" + e);
            Assert.fail("Can not open Login page" + e);
            return null;
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
            enterTextIntoElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
            enterTextIntoElement(inputPassword, password);
    }

    public LoginPage enterUserNameIntoInputSignUp (String userName) {
        enterTextIntoElement(userNameInputSignUp, userName);
        return this;
    }
    public LoginPage enterEmailIntoInputSignUp (String email) {
        enterTextIntoElement(emailInput, email);
        return this;
    }

    public LoginPage enterPasswordInputSignUp (String password) {
        enterTextIntoElement(passwordInputSignUp, password);
        return this;
    }

    public void clickSignUpButton(){
        clickElement(buttonSignUp);
    }
    public void clickButtonLogin() {
       clickElement(buttonLogin);
    }

   public boolean isErrorMessageDisplayed () {
       if (isElementDisplayed(errorMessage)) {
           return true;
       } else {
           logger.info("Message is not displayed");
           return false;
       }
   }
   public boolean isSignInButtonDisplayed(){
        if (isElementDisplayed(buttonLogin)) {

            return true;
        } else {
            logger.info("Sign in button is not displayed!");
            return false;
        }
   }

   public WebElement getErrorMessageSignUp (String invalidMessage) {
        try {

            return webDriver.findElement(By.xpath(String.format(errorMessageSignUp, invalidMessage)));
        }catch (Exception e) {
            logger.info("No element found " + e);
            return null;
       }
   }

   public LoginPage checkErrorMessage (String expectedErrorMessage){
        Assert.assertTrue("Error message is not displayed", getErrorMessageSignUp(expectedErrorMessage).isDisplayed());
        return this;
   }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickButtonLogin();
        return new HomePage(webDriver);
    }

    public LoginPage checkErrorMessages(String expectedErrors) {
        //error1,error2 --> array[0] = error1, array[1] = error2..
        String[] expectedErrorArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be" + expectedErrorArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrorArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorArray.length; i++) {
            softAssertions.assertThat(expectedErrorArray[i]).as("Message is not equal ").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }


    public void checkErrorMessageInvalidLogin(String expectedError) {
            Assert.assertEquals("Incorrect error message is displayed", expectedError, errorMessage.getText());
    }
}
