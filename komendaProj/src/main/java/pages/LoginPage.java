package pages;

import io.qameta.allure.Step;
import libs.DB_getPassword;
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
import pages.elements.HeaderElement;

import java.sql.SQLException;
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

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;

    @FindBy(xpath = ALERT_XPATH)
    private List<WebElement> alertMessages;

    @FindBy(xpath = ".//*[@class='alert alert-danger text-center' and text()='Invalid username  pasword']")
    private WebElement signInError;

    private static final String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public static final String ALERT_XPATH = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private final String parameterizedAlert = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    @Step
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
    @Step
    public void enterUserNameIntoInputLogin(String userName) {
//        try {
//            WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
//            inputUserName.clear();
//            inputUserName.sendKeys(username);
//            logger.info("login was inputted");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
            enterTextInToElement(inputUserName, userName);
    }
    @Step
    public void enterPasswordIntoInputPassword(String password) {
        enterTextInToElement(inputPassword, password);
    }
    @Step
    public void clickOnButtonLogin() {
       clickOnElement(buttonLogin);
    }
    @Step
    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }
    @Step
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
    @Step
    public LoginPage enterUserNameInRegistrationForm(String userName) {
        enterTextInToElement(inputLoginRegistration, userName);
        return this;
    }
    @Step
    public LoginPage enterEmailInRegistrationForm(String email){
        enterTextInToElement(inputEmailRegistration, email);
        return this;
    }
    @Step
    public LoginPage enterPasswordInRegistrationForm(String password){
        enterTextInToElement(inputPasswordRegistration, password);
        return this;
    }
    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        // error1, error2 -> array[0] = error1, array[1] = error2
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors)  {
            actualTextFromErrors.add(element.getText());

        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equals").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

        return this;
    }
    @Step
    public LoginPage checkThreeAlertMessagesAreDisplayed() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(6));
        webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(ALERT_XPATH), 3));
        Assert.assertEquals("Validation doesn't work", 3, alertMessages.size());

        return this;
    }
    @Step
    public LoginPage checkErrorMessageWithText(String textMessage) {
        Assert.assertTrue("Text \"" + textMessage + "\" not found", isElementDisplayed(String.format(parameterizedAlert, textMessage)));
        return this;
    }
    @Step
    public LoginPage checkLoginInErrorMessage(String expectedErrorLogin) {
        Assert.assertTrue("Message error login and password is not displayed", isElementDisplayed(signInError));
        Util.waitABit(1);
        Assert.assertEquals("Wrong message is displayed", expectedErrorLogin, signInError.getText());
        return this;
    }

    @Step
    public HomePage fillingLoginFromDB(String login) throws SQLException, ClassNotFoundException {
        DB_getPassword db_getPassword = new DB_getPassword();
        enterUserNameIntoInputLogin(login);
        enterPasswordIntoInputPassword(db_getPassword.getPassForLogin(login));
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }
}





