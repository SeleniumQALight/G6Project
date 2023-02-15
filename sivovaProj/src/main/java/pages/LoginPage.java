package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage(){
        try{
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Login page was opened");
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
        enterTextIntoElement(userNameInputSignUp,userName);
        return this;
    }
    public LoginPage enterEmailIntoInputSignUp (String email) {
        enterTextIntoElement(emailInput,email);
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
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickButtonLogin();
        return new HomePage(webDriver);
    }

}
