package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    List<WebElement> errorMessageOnRegisterForm;

    private String errorMessageOnRegisterFormByXpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    private String errorMessageOnRegisterFormByXpathWithParamText = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

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
        } catch (Exception e) {
            logger.error("Can't open Login page" + e);
            Assert.fail("Can't open Login page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
        enterTextIntoElement(inputUserNameLogin, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordLogin, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }

    public boolean isButtonLoginDisplayed() {
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public void enterNameIntoInputUserNameRegister(String userName) {
        enterTextIntoElement(inputUserNameRegister, userName);
    }

    public void enterEmailIntoFormRegister(String email) {
        enterTextIntoElement(inputEmailRegister, email);
    }

    public void enterPasswordIntoFormRegister(String password) {
        enterTextIntoElement(inputPasswordRegister, password);
    }

    public void checkIsThreeErrorMessagesDisplayed() {
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorMessageOnRegisterFormByXpath), 3));
    }

    public void checkErrorMessageWithTextInList(String errorText) {
        Assert.assertTrue("element with matching error text wasn't found", isTextInWebElementListPresent(errorMessageOnRegisterForm, errorText));
    }

    public void checkErrorMessageWithTextByParamLocator(String errorText) {
        Assert.assertTrue("element with matching error text wasn't found", isElementDisplayed(String.format(errorMessageOnRegisterFormByXpathWithParamText, errorText)));
    }
}
