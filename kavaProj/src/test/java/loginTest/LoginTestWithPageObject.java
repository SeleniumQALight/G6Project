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
        loginPage.clickOnButtonLogin();
        homePage.isSignOutButtonDisplayed();


    }

    @Test
    public void negativeLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123aa");
        loginPage.clickOnButtonLogin();
        loginPage.isSignInButtonDisplayed();
        loginPage.isSignOutButtonNotDisplayed();


    }
}
