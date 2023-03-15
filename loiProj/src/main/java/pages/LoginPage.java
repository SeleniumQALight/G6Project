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
    private WebElement inputUserNameLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLogin;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegister;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegister;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegister;

    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> errorMessageOnRegisterForm;

    private String errorMessageOnRegisterFormByXpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    private String errorMessageOnRegisterFormByXpathWithParamText = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

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
            logger.error("Can't open Login page" + e);
            Assert.fail("Can't open Login page" + e);
        }
    }

    @Step
    public void enterUserNameIntoInputLogin(String userName) {
        enterTextIntoElement(inputUserNameLogin, userName);
    }

    @Step
    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordLogin, password);
    }

    @Step
    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    @Step
    public boolean isButtonLoginDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    @Step
    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    @Step
    public void enterNameIntoInputUserNameRegister(String userName) {
        enterTextIntoElement(inputUserNameRegister, userName);
    }

    @Step
    public void enterEmailIntoFormRegister(String email) {
        enterTextIntoElement(inputEmailRegister, email);
    }

    @Step
    public void enterPasswordIntoFormRegister(String password) {
        enterTextIntoElement(inputPasswordRegister, password);
    }

    @Step
    public void checkIsThreeErrorMessagesDisplayed() {
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorMessageOnRegisterFormByXpath), 3));
    }

    @Step
    public void checkErrorMessageWithTextInList(String errorText) {
        Assert.assertTrue("element with matching error text wasn't found", isTextInWebElementListPresent(errorMessageOnRegisterForm, errorText));
    }

    @Step
    public void checkErrorMessageWithTextByParamLocator(String errorText) {
        Assert.assertTrue("element with matching error text wasn't found", isElementDisplayed(String.format(errorMessageOnRegisterFormByXpathWithParamText, errorText)));
    }

    @Step
    public void checkErrorsMessages(String expectedErrors) {
        // error1,error2 -> array[0] = error1, array[1] = error2
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be" + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorMessageOnRegisterFormByXpath), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrorsArray.length, errorMessageOnRegisterForm.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: errorMessageOnRegisterForm) {
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message isn't equals").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
    }

}
