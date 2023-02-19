package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameintoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.fillingLoginFormWithInvalidCred();

        Assert.assertFalse("Sign out button is displayed", homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("Sign in button is not displayed", loginPage.isButtonSignInDisplayed());
    }
}
