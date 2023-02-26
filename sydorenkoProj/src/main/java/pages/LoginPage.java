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
    private String locatorForFieldValidationError = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";
    private static final String listOfErrorsLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
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

    public void enterEmailIntoEmailRegisterField(String email) {
        enterTextIntoElement(emailRegisterField, email);
    }

    public void enterPasswordIntoPasswordRegisterField(String password) {
        enterTextIntoElement(passwordRegisterField, password);
    }

    public boolean isFieldValidationErrorDisplayed(String error) {
        return isElementDisplayed(locatorForFieldValidationError, error);
    }

    public void checkErrorsMessages(String expectedErrors) {
//        error1,error2,error3 -> array[0]=error1,array[1]=error2,array[2]=error3
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10.withMessage("Number of messages should be " + expectedErrorsArray.length).until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equals ").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
    }
}
