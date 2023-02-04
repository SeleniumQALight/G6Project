package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {


    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed",
                homePage.isButtonSignOutDisplayed());
    }


    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwertwwwy");
        loginPage.clickOnButtonLogin();
        Assert.assertFalse("Button is not displayed",
                homePage.isButtonSignOutDisplayed());
        Assert.assertTrue("Button is not displayed",
                homePage.isButtonSignInDisplayed());


    }






}
