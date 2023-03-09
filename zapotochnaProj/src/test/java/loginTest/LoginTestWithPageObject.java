package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;


import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)


    public void validLogin() {

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button SignOut is not displayed",
                homePage
                        .getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void inValidLogin() {

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin("invalidtext");
        loginPage.enterPasswordIntoInputPassword("invalidtext");
        loginPage.clickOnButtonLogin();


        Assert.assertFalse("Button SignOut is not displayed",
                homePage
                        .getHeaderElement().isButtonSignOutDisplayed());

        Assert.assertTrue("Button SignIn is not displayed", loginPage.isButtonSignInDisplayed());

    }

    //hw 5:
    //TODO
    @Test
    public void invalidLoginRedMessages() {

    }

    @Test
    public void validLoginWithExcel() throws IOException { //throws IOException - означає що тут можуть бути ексепшини. просто повідомлення.
        Map<String, String> dataFirValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin(dataFirValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataFirValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button SignOut is not displayed",
                homePage
                        .getHeaderElement().isButtonSignOutDisplayed());
    }

}


