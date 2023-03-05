package loginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }


    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("qwerty123456");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                loginPage.isButtonSignInDisplayed());
        Assert.assertFalse("Button is displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void checkFormRegistration(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationField("tr");
        loginPage.enterEmailIntoEmailField("test.com");
        loginPage.enterPasswordIntoPasswordRegistrationField("123");
        loginPage.checkErrorCountMessage(3);

        Assert.assertTrue("Name field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("Username must be at least 3 characters."));
        Assert.assertTrue("Email field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("You must provide a valid email address."));
        Assert.assertTrue("Password field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("Password must be at least 12 characters."));
    }
}
