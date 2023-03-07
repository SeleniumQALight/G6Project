package LoginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.CommonActionsWithElements;
import pages.elements.HeaderElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {


    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }



    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String,String> dataValidLogin=
                ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }


    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwertwwwy");
        loginPage.clickOnButtonLogin();
        Assert.assertFalse("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignInDisplayed());


    }


    @Test
    public void checkTextErrorInFieldRegister() {
        loginPage.openLoginPage();
        loginPage
                .enterUserNameIntoInputUserNameForRegister("tr")
                .enterEmailIntoInputEmailForRegister("test.com" )
                .enterPasswordIntoInputPasswordForRegister("123")
                .checkSizeOfErrorsList(3)
                .checkUserNameError("Username must be at least 3 characters.")
                .checkEmailError("You must provide a valid email address.")
                .checkPasswordError("Password must be at least 12 characters.");
    }


    @Test
    public void checkUserNameTextErrorInFieldRegister() {
        loginPage.openLoginPage();
        loginPage
                .enterUserNameIntoInputUserNameForRegister("tr")
                .checkUserNameError("Username must be at least 3 characters.");
    }

    @Test
    public void checkEmailTextErrorInFieldRegister() {
        loginPage.openLoginPage();
        loginPage
                .enterEmailIntoInputEmailForRegister("test.com")
                .checkEmailError("You must provide a valid email address.");
    }


    @Test
    public void checkPasswordTextErrorInFieldRegister() {
        loginPage.openLoginPage();
        loginPage.enterPasswordIntoInputPasswordForRegister("123")
                .checkPasswordError("Password must be at least 12 characters.");
    }



}
