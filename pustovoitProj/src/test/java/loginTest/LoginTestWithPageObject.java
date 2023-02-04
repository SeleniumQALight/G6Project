package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN;
import static libs.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(VALID_LOGIN);
        loginPage.enterPaswordIntoInputPassword(VALID_PASSWORD);
        loginPage.clickOnButtonLogin();
         
        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void notValidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(VALID_LOGIN);
        loginPage.enterPaswordIntoInputPassword("123456qwerty1");
        loginPage.clickOnButtonLogin();

        Assert.assertFalse("Button SignOut is displayed", homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("Button SignIn is displayed", loginPage.isButtonSignInDisplayed());

    }

}

