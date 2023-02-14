package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy (xpath = ".//input[@name='username'  and @class='form-control']")
    private WebElement signupUsername;

    @FindBy (xpath = ".//input[@name='email'  and @class='form-control']")
    private WebElement signupEmail;

    @FindBy (xpath = ".//input[@name='password'  and @class='form-control']")
    private WebElement signupPassword;

    @FindBy (xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible'" +
            "and contains(text(), 'Username must be at least 3 characters.')]")
    private WebElement usernameAlertMessage;

    @FindBy (xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible'" +
            "and contains(text(), 'You must provide a valid email address.')]")
    private WebElement emailAlertMessage;

    @FindBy (xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible'" +
            "and contains(text(), 'Password must be at least 12 characters.')]")
    private WebElement passwordAlertMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
//-------------------------------------------------------------------------------------------------------------
//    public void openLoginPage() {
//        try {
//            webDriver.get("https://qa-complexapp.onrender.com/");
//            logger.info("LoginPage is opened");
//
//        } catch (Exception e) {
//            logger.error("Can not open Login Page" + e);  //Write message into log-file
//            Assert.fail("Can not open Login Page" + e);  //Write message into console
//        }
//    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("LoginPage is opened");

        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);  //Write message into log-file
            Assert.fail("Can not open Login Page" + e);  //Write message into console
        }
        return this;
    }
//-------------------------------------------------------------------------------------------------------------

    public void enterUserNameIntoInputLogin(String userName) {
//        try {

////            WebElement inputUserName =
////                    webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"))
////       !!! the same as:   @FindBy(xpath = "...") private WebElement inputUserName;

//            inputUserName.clear();
//            inputUserName.sendKeys(userName);
//            logger.info("login was inputted");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserName, userName);
    }

    public LoginPage enterDataIntoUsernameField(String userName) {
        enterTextIntoElement(signupUsername, userName);
        return  this;
    }

//-------------------------------------------------------------------------------------------------------------

    public void enterPasswordIntoInputpassword(String password) {
//        try {
////            WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("Password was entered");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPassword, password);

    }
    public LoginPage enterDataIntoPasswordField(String password) {
        enterTextIntoElement(signupPassword, password);
        return this;
    }


//-------------------------------------------------------------------------------------------------------------
    public LoginPage enterDataIntoEmailField(String password) {
    enterTextIntoElement(signupEmail, password);
    return this;
}


    public void clickOnButtonLogin() {
//        try {
////            WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
//            buttonLogin.click();
//            logger.info("Button was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonLogin);

    }

    public HomePage fillingLoginFormWithValidCred() {
        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LIGIN);
        enterPasswordIntoInputpassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();

        return new HomePage(webDriver);
    }

    public boolean isButtonSignInDisplayed(){

        return isObjectDisplayed(buttonLogin);
    }


    public LoginPage checkIsUsernameAlertMessageContainText(String expectedMessage){
        Assert.assertEquals("Wrong text in the Username message element", expectedMessage, usernameAlertMessage.getText());
        return this;
    }

    public LoginPage checkIsEmailAlertMessageContainText(String expectedMessage){
        Assert.assertEquals("Wrong text in the Email message element", expectedMessage, emailAlertMessage.getText());
        return this;
    }

    public LoginPage checkIsPasswordAlertMessageContainText(String expectedMessage){
        Assert.assertEquals("Wrong text in the Email message element", expectedMessage, passwordAlertMessage.getText());
        return this;
    }
//------------------------------------------------------------------------------------------------------------------------
    private String alertMessageText = ".//*[text()='%s']";

    public List<WebElement> getAlertMessage(String alertText1){
        return webDriver.findElements(By.xpath(String.format(alertMessageText, alertText1)));
    }

    public LoginPage checkAlertMessage(String alertText2) {
        Assert.assertEquals("Number of alerts with text", 1, getAlertMessage(alertText2).size());
        return this;
    }
//------------------------------------------------------------------------------------------------------------------------

}
