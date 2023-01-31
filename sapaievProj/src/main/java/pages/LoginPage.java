package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;


    public LoginPage(WebDriver webDriver) {

        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Login page was open");
        } catch (Exception e) {
            logger.error("Can not open login page" + e);
            Assert.fail("Can not open login page" + e);
        }
    }


    public void enterUserNameIntoInputLogin(String userName) {

        enterTextIntiElement(inputUserName,userName);
    }


    public void enterPasswordIntoInputPassword(String password) {

        enterTextIntiElement(inputPassword,password);
    }

    public void clickOnButtonLogin() {

        clickOnElement(buttonLogin);
    }






}

