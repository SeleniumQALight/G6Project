package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
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

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    private String actualAlertMessage = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public LoginPage openLoginPage(){
        try {
            webDriver.get(baseURL + getRelativeURL());
            logger.info("Login page was opened");
            logger.info(baseURL);
        } catch (Exception e){
            logger.error("Can't open login page" +e);
            Assert.fail("Can't open login page" +e);
        }
        return this;
    }

    public void enterUserNameIntoInputLogin(String userName) {

        enterTextIntoElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();

        return new HomePage(webDriver);
    }

    public LoginPage enterUserNameIntoRegistrationInput(String userName) {
        enterTextIntoElement(inputUserNameRegistration, userName);
        return this;
    }

    public LoginPage enterEmailIntoRegistrationInput(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordIntoRegistrationInput(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage checkTextInAlertMessage(String expectedAlertTitle) {
        Assert.assertTrue("Alert message does not correspond", findAlertMessage(expectedAlertTitle).isDisplayed());
        return this;
    }

    private WebElement findAlertMessage(String expectedAlertTitle) {
        return webDriver.findElement(By.xpath(String.format(actualAlertMessage, expectedAlertTitle)));
    }


}
