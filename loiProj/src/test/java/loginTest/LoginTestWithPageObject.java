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

        Assert.assertTrue("Button isn't displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaautoInvalidLogin");
        loginPage.enterPasswordIntoInputPassword("12345qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button isn't displayed", loginPage.isButtonLoginDisplayed());
        Assert.assertFalse("Button is displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }
}
