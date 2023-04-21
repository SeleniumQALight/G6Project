package pages;

import io.qameta.allure.Step;
import libs.DB_Util_HomeWork;
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

import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;


    //Первое задание дошнего задания №4
    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement userNameFieldForRegister;


    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement emailFieldForRegister;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement passwordFieldForRegister;


    private String textError = "//div[text()='%s']";

    private String errorsForRegistr = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    @FindBy(xpath = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;


    @FindBy(xpath = "//div[@class='alert alert-danger text-center' and text()='Invalid username / password.']")
    private WebElement loginErrorText;

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement allertErrorForLogin;



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
            webDriver.get(base_URL + getRelativeURL());
            logger.info("Login page was open");
            logger.info(base_URL);
        } catch (Exception e) {
            logger.error("Can not open login page" + e);
            Assert.fail("Can not open login page" + e);
        }
    }

    @Step
    public void enterUserNameIntoInputLogin(String userName) {

        enterTextIntiElement(inputUserName, userName);
    }

    @Step
    public void enterPasswordIntoInputPassword(String password) {

        enterTextIntiElement(inputPassword, password);
    }

    @Step
    public void clickOnButtonLogin() {

        clickOnElement(buttonLogin);
    }


    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }


    public HomePage fillingLoginFormWithValidCredWithDataBase() throws SQLException, ClassNotFoundException {
        enterUserNameIntoInputLogin("newqaauto");
        DB_Util_HomeWork db_util_homeWork=new DB_Util_HomeWork();
        enterPasswordIntoInputPassword(db_util_homeWork.getPassForLogin("newqaauto"));
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }


   public List<WebElement> getErrorList(String title) {
        return webDriver.findElements(By.xpath(title));
    }

    public LoginPage checkSizeOfErrorsList(int size) {
        webDriverWait15.until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorsForRegistr),size));
        Assert.assertTrue("The number of errors is"+size, getErrorList(errorsForRegistr).size() == size);
        return this;
    }


    public LoginPage enterUserNameIntoInputUserNameForRegister(String userName) {
        enterTextIntiElement(userNameFieldForRegister, userName);
        return this;
    }

    public LoginPage checkUserNameError(String text) {
        isElementDisplayed(String.format(textError, text));
        WebElement error = webDriver.findElement(By.xpath(String.format(textError, text)));
        WebDriverWait wait10 = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait10.until(ExpectedConditions.visibilityOf(error));
        Assert.assertTrue("The userName error text is not displayed.", isElementDisplayed(String.format(textError, text)));
        return this;
    }


    public LoginPage enterEmailIntoInputEmailForRegister(String email) {
        enterTextIntiElement(emailFieldForRegister, email);
        return this;
    }

    public LoginPage checkEmailError(String text) {
        isElementDisplayed(String.format(textError, text));
        WebElement error = webDriver.findElement(By.xpath(String.format(textError, text)));
        WebDriverWait wait10 = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait10.until(ExpectedConditions.visibilityOf(error));
        Assert.assertTrue("The email error text is not displayed.", isElementDisplayed(String.format(textError, text)));
        return this;
    }

    public LoginPage enterPasswordIntoInputPasswordForRegister(String password) {
        enterTextIntiElement(passwordFieldForRegister, password);
        return this;
    }

    public LoginPage checkPasswordError(String text) {
        isElementDisplayed(String.format(textError, text));
        WebElement error = webDriver.findElement(By.xpath(String.format(textError, text)));
        WebDriverWait wait10 = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait10.until(ExpectedConditions.visibilityOf(error));
        Assert.assertTrue("The password error text is not displayed.", isElementDisplayed(String.format(textError, text)));
        return this;
    }


    //Второй вариант проверки списка ошибок
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray=expectedErrors.split(",");
        webDriverWait15.withMessage("Namber messages should be"+expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorsForRegistr),expectedErrorsArray.length));

        Util.waitABit(1);

        Assert.assertEquals("Number or messages", expectedErrorsArray.length, listOfErrors.size());


        ArrayList<String> actualTextFromErrors=new ArrayList<>();

        for (WebElement element:listOfErrors){
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions=new SoftAssertions();
        for (int i=0; i<expectedErrorsArray.length; i++){
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equals").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }

    public LoginPage checkLoginError(){
        Assert.assertTrue(isElementDisplayed(loginErrorText));
        return this;
    }


    public LoginPage checkErrorsMessageForLogIn() {
       Assert.assertTrue("Error text for log in is displayed",isElementDisplayed(loginErrorText));
        return this;
    }


    public void checkAlertInCenter(String expectedText) {
        Assert.assertEquals("Message in center in allert", expectedText, allertErrorForLogin.getText());


    }
}

