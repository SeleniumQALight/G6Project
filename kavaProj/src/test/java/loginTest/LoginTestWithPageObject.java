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
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                headerElement.isSignOutButtonDisplayed());


    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                headerElement.isSignOutButtonDisplayed());


    }


    @Test
    public void negativeLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123aa");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Sign In button is not displayed",
                loginPage.isSignInButtonDisplayed());

        Assert.assertFalse("Sign Out button is displayed",
                headerElement.isSignOutButtonDisplayed());


    }
}


