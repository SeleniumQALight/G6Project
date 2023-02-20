package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    private TestData testData = new TestData();

    public void openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
        // try {
        //      WebElement inputUserName = webDriver.findElement(
        //              By.xpath(".//input[@name='username' and @placeholder='Username']"));
        //     inputUserName.clear();
        //     inputUserName.sendKeys(userName);
        //       logger.info("login was inputted");
        //   } catch (Exception e){
        //       printErrorAndStopTest(e);
        //   }
        enterTextInToElement(inputUserName, userName);

    }

    public void enterPasswordIntoInputPassword(String password) {
        //  try {
        // WebElement inputPassword =
        //       webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        //         inputPassword.clear();
        //          inputPassword.sendKeys(password);
        //          logger.info("Password was entered");
        //      }catch (Exception e){
        //      printErrorAndStopTest(e);
        //  }
        enterTextInToElement(inputPassword, password);
    }

    public void clickButtonLogin() {
        clickOnElement(buttonLogin);
    }

    public HomePage fillingLoginFormWhitValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickButtonLogin();
        return new HomePage(webDriver);
    }
}

