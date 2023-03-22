package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LogInWithKeys extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void logInWithTabAndEnter() {
        loginPage.openLoginPage();
        loginPage.usersPressesKeyTabTime(2);
        loginPage.usersPressesKeyWordTime("qaauto", 1);
        loginPage.usersPressesKeyTabTime(1);
        loginPage.usersPressesKeyWordTime("123456qwerty", 1);
        loginPage.usersPressesKeyTabTime(1);
        loginPage.usersPressesKeyEnterTime(1);

        Assert.assertTrue("Button is not displayed", headerElements.isButtonSignOutDisplayed());
    }
}
