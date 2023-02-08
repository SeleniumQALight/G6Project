package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaaut");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertFalse("Sign Out button is displayed", homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("Sign in button is not displayed", loginPage.isSignInButtonDisplayed());
    }
}
