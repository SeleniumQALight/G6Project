package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;


public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;
    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement usernameRegisterField;
    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement emailRegisterField;
    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement passwordRegisterField;
    String locatorForFieldValidationError = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";
//    @FindBy(xpath = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='Username must be at least 3 characters.']")
//    private WebElement registrationFormUserNameFieldAlert;
//    @FindBy(xpath = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='You must provide a valid email address.']")
//    private WebElement registrationFormEmailFieldAlert;
//    @FindBy(xpath = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='Password must be at least 12 characters.']")
//    private WebElement registrationFormPasswordFieldAlert;

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
            logger.error("Can not open Login page" + e);
            Assert.fail("Can not open Login page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
//        try {
//            WebElement inputUserName = webDriver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']"));
//            inputUserName.clear();
//            inputUserName.sendKeys(userName);
//            logger.info("Login was inputted");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserName, userName);
    }

    public void actionsSendKeys(String text) {
        Actions action = new Actions(webDriver);
        action.sendKeys(text).build().perform();
    }

    public void openNewTabWithSameUrl() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

    public void switchToFirstTabAndRefresh() {
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0));
        webDriver.navigate().refresh();
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public void enterNameIntoNameRegisterField(String name) {
        enterTextIntoElement(usernameRegisterField, name);
    }

    public void enterTextIntoRegistrationFormFieldsUsingKeys(String name, String email, String password) {
        Actions action = new Actions(webDriver);
        action.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, name, Keys.TAB, email, Keys.TAB, password, Keys.ENTER).build().perform();
    }

    public void enterEmailIntoEmailRegisterField(String email) {
        enterTextIntoElement(emailRegisterField, email);
    }

    public void enterPasswordIntoPasswordRegisterField(String password) {
        enterTextIntoElement(passwordRegisterField, password);
    }

    public boolean isFieldValidationErrorDisplayed(String error) {
        return isElementDisplayed(locatorForFieldValidationError, error);
    }
//    public boolean isEmailFieldValidationErrorDisplayed() {
//        return isElementDisplayed(registrationFormEmailFieldAlert);
//    }
//    public boolean isPasswordFieldValidationErrorDisplayed() {
//        return isElementDisplayed(registrationFormPasswordFieldAlert);
//    }
}
