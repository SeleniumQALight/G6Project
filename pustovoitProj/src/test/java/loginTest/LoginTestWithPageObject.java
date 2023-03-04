package loginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

import static libs.TestData.VALID_LOGIN;
import static libs.TestData.VALID_PASSWORD;
import static pages.CommonActionsWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(VALID_LOGIN);
        loginPage.enterPaswordIntoInputPassword(VALID_PASSWORD);
        loginPage.clickOnButtonLogin();
         
        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void notValidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(VALID_LOGIN);
        loginPage.enterPaswordIntoInputPassword("123456qwerty1");
        loginPage.clickOnButtonLogin();

        Assert.assertFalse("Button SignOut is displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button SignIn is displayed", loginPage.isButtonSignInDisplayed());

    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPaswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

}

