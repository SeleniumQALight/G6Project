package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement userNameRegistrationField;

    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement passwordRegistrationField;

    String errorMessage = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage was opened");
        } catch (Exception e){
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
//
        enterTextInToElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
//        try {
//            //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("Password was entered");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextInToElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
//        try{
//            //WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
//            buttonLogin.click();
//            logger.info("Button was clicked");
//        }catch(Exception e){
//            printErrorAndStopTest(e);
//        }
//    }
        clickOnElement(buttonLogin);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginForWithValidCred() {
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public void enterUserNameIntoRegistrationField(String userName) {
        enterTextInToElement(userNameRegistrationField, userName);
    }

    public void enterEmailIntoEmailField(String email) {
        enterTextInToElement(emailField, email);
    }

    public void enterPasswordIntoPasswordRegistrationField(String password) {
        enterTextInToElement(passwordRegistrationField, password);
    }

    public boolean isFieldValidationErrorIsDisplayed(String error) {
        return isElementDisplayed(errorMessage, error);
    }
}



