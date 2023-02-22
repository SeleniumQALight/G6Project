package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsernameRegistration;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailRegistration;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    private String assertions = "//label[@for = '%s']/../div";
    private String listOfAssertion = ".//*[text()='%s']";

    public List<WebElement> getAssertList(String assertion1, String assertion2, String assertion3,String listOfAssertion){
        List <WebElement> assertList = new ArrayList<>();
        assertList.add(webDriver.findElement(By.xpath(String.format(listOfAssertion,assertion1))));
        assertList.add(webDriver.findElement(By.xpath(String.format(listOfAssertion,assertion2))));
        assertList.add(webDriver.findElement(By.xpath(String.format(listOfAssertion,assertion3))));
        return assertList;
    }

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeURL() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String username) {
        //try {
        //WebElement inputUsername = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        // inputUserName.clear();
        // inputUserName.sendKeys(username);
        // logger.info("login was input");
        //}catch (Exception e){
        //    printErrorAndStopTest(e);
        //}
        enterTextInToElement(inputUserName, username);
    }

    public void enterPasswordIntoInputPassword(String password) {
        try{
            //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            inputPassword.clear();
            inputPassword.sendKeys(password);
            logger.info("Password was entered");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
        enterTextInToElement(inputPassword, password);
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonLogin);
    }

    public boolean isButtonSignOutDisplayed(){
        return isElementDisplayed(buttonLogin);
    }

    public void clickOnButtonLogin() {
       clickOnElement(buttonLogin);
    }

    public void clickOnButtonSignUp(){
        clickOnElement(buttonSignUp);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public void enterInvalidUsernameInRegistrationForm(String username){
        enterTextInToElement(inputUsernameRegistration, username);
    }
    public void enterInvalidEmailInRegistrationForm(String email){
        enterTextInToElement(inputEmailRegistration, email);
    }
    public void enterInvalidPassInRegistrationForm(String password){
        enterTextInToElement(inputPasswordRegistration, password);
    }
     public LoginPage fillingRegistationFormWithInvalidCred(){
        enterInvalidUsernameInRegistrationForm(TestData.INVALID_USERNAME);
        enterInvalidEmailInRegistrationForm(TestData.INVALID_EMAIL);
        enterInvalidPassInRegistrationForm(TestData.INVALID_PASSWORD);
        return this;
     }
    public LoginPage checkIsUsernameAssertionsIsDisplayed(String assertions, String expectedUsernameAssertion){
        String assertName = "username-register";
        WebElement expectedUsernameLocator = webDriver.findElement(By.xpath(String.format(assertions, assertName)));
        Assert.assertEquals("Assert username message is not displayed", expectedUsernameAssertion, expectedUsernameLocator.getText());
        return this;
    }
    public LoginPage checkIsEmailAssertionsIsDisplayed(String assertions, String expectedEmailAssertion){
        String assertName = "email-register";
        WebElement expectedEmailLocator = webDriver.findElement(By.xpath(String.format(assertions, assertName)));
        Assert.assertEquals("Assert email message is not displayed", expectedEmailAssertion, expectedEmailLocator.getText());
        return this;
    }

    public LoginPage checkIsPasswordAssertionsIsDisplayed(String assertions, String expectedPasswordAssertion){
        String assertName = "password-register";
        WebElement expectedPasswordLocator = webDriver.findElement(By.xpath(String.format(assertions, assertName)));
        Assert.assertEquals("Assert password message is not displayed", expectedPasswordAssertion, expectedPasswordLocator.getText());
        return this;
    }

     public LoginPage checkAssertsDisplaed(){
        fillingRegistationFormWithInvalidCred();
         String assertion1 = "Username must be at least 3 characters.";
         String assertion2 = "You must provide a valid email address.";
         String assertion3 = "Password must be at least 12 characters.";
        List <WebElement> listOfAssertions = getAssertList(assertion1, assertion2, assertion3,listOfAssertion);
        int counter = listOfAssertions.size();
        if (counter == 3){
            logger.info("All assertions displays");
        }else{
            logger.info("Not all assertions displays");
            Assert.fail("Checking assertions failed");
        }
         checkIsUsernameAssertionsIsDisplayed(assertions,assertion1);
         checkIsEmailAssertionsIsDisplayed(assertions,assertion2);
         checkIsPasswordAssertionsIsDisplayed(assertions,assertion3);
         return this;
    }
}


