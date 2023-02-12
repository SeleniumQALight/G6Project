package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed() );

    }

    @Test
    public void inValidLogin() {

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin("invalidtext");
        loginPage.enterPasswordIntoInputPassword("invalidtext");
        loginPage.clickOnButtonLogin();


        Assert.assertFalse("Button SignOut is not displayed", homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("Button SignIn is displayed", loginPage.isButtonSignInDisplayed());

    }
}


