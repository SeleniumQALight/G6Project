package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterLoginNameIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterLoginNameIntoInputPassword("Failed87654321");
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button is displayed", homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("Button is not displayed", loginPage.isButtonSignInDisplayed());
    }
}
