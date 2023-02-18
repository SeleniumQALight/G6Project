package pages;


import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement signInBtn;
    @FindBy(id = "username-register")
    private WebElement userNameReg;
    @FindBy(id = "email-register")
    private WebElement emailReg;
    @FindBy(id = "password-register")
    private WebElement passwordReg;


    private String errorMessage = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible'and text()='%s']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Login page was open");
        } catch (Exception e) {
            logger.error("Can't Open page " + e);
            Assert.fail("Can't Open page " + e);
        }
        return this;
    }

    public void typeUserName(String userName) {
        typeTextToElement(inputUserName, userName);
    }

    public void typeUserPassword(String password) {
   /*     try {
           // WebElement passwordInput = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            passwordInput.clear();
            passwordInput.sendKeys(password);
            logger.info("Type password ");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }*/
        typeTextToElement(passwordInput, password);
    }

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

    public LoginPage typeUserNameForRegistration(String userName) {
        typeTextToElement(userNameReg, userName);
        return this;
    }

    public LoginPage typeEmailForRegistration(String email) {
        typeTextToElement(emailReg, email);
        return this;
    }

    public LoginPage typePasswordForRegistration(String password) {
        typeTextToElement(passwordReg, password);
        return this;
    }

    public HomePage fillValidCreds() {
        openLoginPage();
        typeUserName(TestData.VALID_LOGIN);
        typeUserPassword(TestData.VALID_PASSWORD);
        clickSignIn();
        return new HomePage(webDriver);
    }

    public boolean isButtonSingInDisplayed() {
        return signInBtn.isDisplayed();
    }

    public LoginPage checkErrorMessageWithText(String message) {
        String errorMessageText = getText(getWebElement(String.format(errorMessage,message)));
        Assert.assertEquals("Error message for field is wrong", message, errorMessageText);
        return this;
    }
}
