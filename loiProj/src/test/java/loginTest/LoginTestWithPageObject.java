package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
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

    @Test
    @Parameters(method = "provideParameters")
    public void invalidLoginWithParam(String userName, String password) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(userName);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button isn't displayed", loginPage.isButtonLoginDisplayed());
        Assert.assertFalse("Button is displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"testTest", " "},
                new Object[]{"   ", "??"}
        };
    }
}
