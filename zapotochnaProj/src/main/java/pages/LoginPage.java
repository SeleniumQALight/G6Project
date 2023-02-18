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
    private WebElement inputPassword;


    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() { //зробили геттер бо є абстрактний клас
        return "/";
    }

    public void openLoginPage() {

        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");

        } catch (Exception e) {
            logger.error("Cannot open page " + e);  // generated to log report

            Assert.fail("Cannot open page" + e);  // generated to report
        }


    }


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

    public void clickOnButtonLogin() {
        clickOnElement(buttonLogin);
    }
    public boolean isButtonSignInDisplayed() {
//        try {
//            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(buttonLogin);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();

        return new HomePage(webDriver);
    }
}
