package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class LoginTestWIthPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin (){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInpuLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed() );
    }

    @Test
    public void invalidLogin (){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInpuLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertFalse("Button is displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button is not displayed", loginPage.isSignInButtonDisplayed());
    }

    @Test
    public void validLoginWithExcel () throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInpuLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed() );
    }

}
