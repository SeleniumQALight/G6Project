package loginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.CommonActionsWithElements;

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

        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());


    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456654321");
        loginPage.clickOnButtonLogin();


        Assert.assertTrue("Sign in button is not displayed", loginPage.isButtonSignInDisplayed());
        Assert.assertFalse("Sign out button is displayed", homePage.isButtonSignOutDisplayed());


    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());

    }

}
