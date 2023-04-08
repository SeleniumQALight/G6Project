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
    @FindBy(xpath = ".//input[@placeholder='Pasword']")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement signInBtn;
    @FindBy(id = "username-register")
    private WebElement userNameReg;
    @FindBy(id = "email-register")
    private WebElement emailReg;
    @FindBy(id = "password-register")
    private WebElement passwordReg;
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;
    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement signInError;
@FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
private WebElement alertInCenter;

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private String errorMessage = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible'and text()='%s']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        try {
            webDriver.get(baseURL + "/");
            logger.info("Login page was open");
            logger.info(baseURL);
        } catch (Exception e) {
            logger.error("Can't Open page " + e);
            Assert.fail("Can't Open page " + e);
        }
        return this;
    }
    @Step
    public LoginPage typeUserName(String userName) {
        typeTextToElement(inputUserName, userName);
        return this;
    }
    @Step
    public LoginPage typeUserPassword(String password) {
   /*     try {
           // WebElement passwordInput = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            passwordInput.clear();
            passwordInput.sendKeys(password);
            logger.info("Type password ");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }*/
        typeTextToElement(passwordInput, password);
        return this;
    }
    @Step
    public HomePage clickSignIn() {
     /*   try {
          //  WebElement signInBtn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
            signInBtn.click();
            logger.info("Sign in clicked");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }*/
        clickOnElement(signInBtn);
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage typeUserNameForRegistration(String userName) {
        typeTextToElement(userNameReg, userName);
        return this;
    }
    @Step
    public LoginPage typeEmailForRegistration(String email) {
        typeTextToElement(emailReg, email);
        return this;
    }
    @Step
    public LoginPage typePasswordForRegistration(String password) {
        typeTextToElement(passwordReg, password);
        return this;
    }
    @Step
    public HomePage fillValidCreds() {
        //  openLoginPage();
        typeUserName(TestData.VALID_LOGIN);
        typeUserPassword(TestData.VALID_PASSWORD);
        clickSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public boolean isButtonSingInDisplayed() {
        return signInBtn.isDisplayed();
    }
    @Step
    public LoginPage checkErrorMessageWithText(String message) {
        String errorMessageText = getText(getWebElement(String.format(errorMessage, message)));
        Assert.assertEquals("Error message for field is wrong", message, errorMessageText);
        return this;
    }
    @Step
    public LoginPage signInAndCheckSignInErrorAlert(String text) {
        clickOnElement(signInBtn);

        Assert.assertTrue("Error notification is not visible",   isElementDisplayed(signInError));
        Assert.assertEquals(text, getText(signInError));
        return this;
    }

    public LoginPage checkErrorMessageUniversal(String message) {
        String[] expectedErrorsArr = message.split(",");
        wait10.withMessage("Number of messages should be " + expectedErrorsArr.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArr.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of messages",expectedErrorsArr.length,listOfErrors.size());
        List<String> actualFromErrors = new ArrayList();
        for (WebElement e : listOfErrors) {
            actualFromErrors.add(e.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArr.length; i++) {
            softAssertions.assertThat(expectedErrorsArr[i]).isIn(actualFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }

    public void checkAlertInCentre(String expectedText) {
        Assert.assertEquals( "Wrong message in alert", expectedText, alertInCenter.getText());
    }
}
