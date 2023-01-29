package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complexapp.onrender.com/");
            logger.info("Login page was opened");
        } catch (Exception e){
            logger.error("Can't open login page" +e);
            Assert.fail("Can't open login page" +e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) {
//        try {
////            WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
//            inputUserName.clear();
//            inputUserName.sendKeys(userName);
//            logger.info("Login was inputted");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
//        try {
////            WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("Password was entered");
//
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
//        try {
////            WebElement buttonLogin = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
//            buttonLogin.click();
//            logger.info("Button was clicked");
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonLogin);
    }
}
