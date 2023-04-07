package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithKeys extends BaseTest {

    @Test
    public void validLoginWithKeys() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.usersPressesKeyTabTime(1);
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.usersPressesKeyTabTime(1);
        loginPage.usersPressesKeyEnterTime(1);

        Assert.assertTrue("Button is not displayed",
                headerElement.isSignOutButtonDisplayed());
    }
}
