package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
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

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible'] ")
    private List<WebElement> listOfErrors;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() { //зробили геттер бо є абстрактний клас
        return "/";
    }

    @Step

    public void openLoginPage() {

        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
            logger.info(base_url);

        } catch (Exception e) {
            logger.error("Cannot open page " + e);  // generated to log report

            Assert.fail("Cannot open page" + e);  // generated to report
        }


    }

    @Step

    public void enterUserNameIntoLogin(String username) {

//        try {
//           // WebElement inputUserName =
//             //       webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
//
//            inputUserName.clear();
//            inputUserName.sendKeys(username);
//            logger.info("login was inputted");
//        } catch (Exception e) {
//printErrorAndStopTest(e);


        enterTextInToElement(inputUserName, username);

    }

    @Step

    public void enterPasswordIntoInputPassword(String password) {
//        try {
//         //   WebElement inputPassword =
//          //          webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("password was inputted");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);

        enterTextInToElement(inputPassword, password);

    }

    @Step

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    @Step

    public boolean isButtonSignInDisplayed() {

        return isElementDisplayed(buttonLogin);
    }

    @Step

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();

        return new HomePage(webDriver);
    }

    @Step

    public LoginPage enterUserNameInRegistrationForm(String userName) {
        enterTextInToElement(inputLoginRegistration, userName);
        return this;

    }

    @Step

    public LoginPage enterUserEmailInRegistrationForm(String userEmail) {
        enterTextInToElement(inputEmailRegistration, userEmail);
        return this;
    }

    @Step
    public LoginPage enterPasswordInRegistrationForm(String userPassword) {
        enterTextInToElement(inputPasswordRegistration, userPassword);
        return this;
    }

    @Step

    public LoginPage checkErrorsMessages(String expectedErrors) {
        //сюди передаємо ерори у вигляді стрінги , потім розпарсимо їх/сплітимо по комі

        //error1, error2, error3 -> array[0] = error1, array1[1] = error2

        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of Messages should be  " + expectedErrorsArray.length)  //цей меседж побачимо тоді, коли не вийде чекати Until . по аналогії як з асертом.
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        Util.waitABit(1);// почекай секунду, бо не можу використати інши й вейт. бо не знаємо що саме чекати.
        Assert.assertEquals("number of messages ", expectedErrorsArray.length, listOfErrors.size());


        ArrayList<String> actualTextFromErrors = new ArrayList<>();


        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }

        //softasserch корисний для таблиць і блоків.
        SoftAssertions softAssertions = new SoftAssertions(); //буде робити перевірку тільки тоді, коли assertAll(). коли його використовуємо зразу місце де перевірка.
        for (int i = 0; i < expectedErrorsArray.length; i++) {

            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not matched")
                    .isIn(actualTextFromErrors); //якщо хоч один не співпав, тест буде червоним. і виведе всі
        }
        softAssertions.assertAll();


        return this;
    }


}
