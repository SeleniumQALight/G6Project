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

    private String alertMessageText = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and contains(text(), '%s')]";




    String signUpAlertMessages = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";



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

    @Override
    String getRelativeURL() {
        return "/";
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
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
//        openLoginPage();
        enterUserNameIntoInputLogin(TestData.VALID_LIGIN);
        enterPasswordIntoInputpassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();

        return new HomePage(webDriver);
    }

    public boolean isButtonSignInDisplayed(){

        return isObjectDisplayed(buttonLogin);
    }



    public LoginPage checkAlertMessageContainText(String expectedMessage){
        Assert.assertTrue("Element is not displayed", isElementDisplayed(getAlertMessage(expectedMessage)));
        return this;
    }

    public WebElement getAlertMessage(String alertText1){
      return webDriver.findElement(By.xpath(String.format(alertMessageText, alertText1)));
    }



//------------------------------------------------------------------------------------------------------------------------

    public List<WebElement> getAlertMessageCheckList(String alertText1){
        return webDriver.findElements(By.xpath(String.format(alertMessageText, alertText1)));
    }

    public LoginPage checkAlertMessageIsOnlyOne(String alertText2) {
        Assert.assertEquals("Number of alerts with text", 1, getAlertMessageCheckList(alertText2).size());
        return this;
    }
//------------------------------------------------------------------------------------------------------------------------

    public List<WebElement> getAlertMessageList(){
       return webDriver.findElements(By.xpath(signUpAlertMessages));
    }

    // додати очікування всередину
    public LoginPage checkAlertMessageQuantity() {
        Assert.assertEquals("Number of alerts is not 3", 3, getAlertMessageList().size());
        return this;
    }

}
