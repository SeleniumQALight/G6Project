package pages;

import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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


//-----------------
    private String signInErrorMessageText = ".//div[@class='alert alert-danger text-center' and contains(text(), '%s')]";


    @FindBy (xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement signInErrorMessageWithOutText;
//-----------------

    @FindBy (xpath = signUpAlertMessages)
    private List<WebElement> listOfErrors;

    private static final String signUpAlertMessages = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";





    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }


//-------------------------------------------------------------------------------------------------------------

    @Override
    String getRelativeURL() {
        return "/";
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage is opened");
            logger.info(base_url);      // робиться запис про URL в логах, при запуску метода openLoginPage
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);  //Write message into log-file
            Assert.fail("Can not open Login Page" + e);  //Write message into console
        }
        return this;
    }
//-------------------------------------------------------------------------------------------------------------

    public void enterUserNameIntoInputLogin(String userName) {

        enterTextIntoElement(inputUserName, userName);
    }

    public LoginPage enterDataIntoUsernameField(String userName) {
        enterTextIntoElement(signupUsername, userName);
        return  this;
    }

//-------------------------------------------------------------------------------------------------------------

    public void enterPasswordIntoInputPassword(String password) {

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
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
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



    public List<WebElement> getAlertMessageList() {
        WebDriverWait waitAlertMessages = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        return waitAlertMessages.until(ExpectedConditions.numberOfElementsToBe(By.xpath(signUpAlertMessages), 3));
    }

    public LoginPage checkAlertMessageQuantity() {
        Assert.assertEquals("Number of alerts is not 3", 3, getAlertMessageList().size());
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {    // бере список текстів помилок, підрахує
        // error1, error2, -> array[0] = error1, array[1] = error2,
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(signUpAlertMessages), expectedErrorsArray.length));

        Util.waitABit(1);  //  додаємо очікування 1 сек
        Assert.assertEquals("Number of messages", expectedErrorsArray.length, listOfErrors.size());  // робимо перевірку після очікування в 1 сек

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equal").isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

        return this;
    }



    public LoginPage enterUsernameAndPassword (String userName, String password) {
        enterUserNameIntoInputLogin(userName);
        enterPasswordIntoInputPassword(password);
        clickOnButtonLogin();
        return this;
    }

    public LoginPage checkSignInErrorMessageIsVisible() {
        Assert.assertTrue("Error element is not visible", isElementDisplayed(signInErrorMessageWithOutText));
        return this;
    }


    public LoginPage checkSignInErrorMessageContainText(String expectedMessage){
        Assert.assertTrue("Element is not displayed", isElementDisplayed(getSignInErrorMessage(expectedMessage)));
        return this;
    }

    public WebElement getSignInErrorMessage(String SignInErrorMessageText){
        return webDriver.findElement(By.xpath(String.format(signInErrorMessageText, SignInErrorMessageText)));
    }



}