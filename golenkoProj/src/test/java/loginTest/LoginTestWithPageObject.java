package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String ERROR_LOGIN_PASSWORD_INCORRECT = "Invalid username pasword";

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());

    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaautoinvalid");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertFalse("Button \"Sign out\" is displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button \"Sign in\" is not displayed",
                loginPage.isButtonSignInDisplayed());

    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, password = {1}")
    public void invalidLoginWithParams(String login, String password, String expectedError) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(login);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();
        loginPage.checkErrorMessageForLogin(expectedError);

    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"", "123456qwerty", ERROR_LOGIN_PASSWORD_INCORRECT},
                new Object[]{"qaauto", "", ERROR_LOGIN_PASSWORD_INCORRECT},
                new Object[]{"qaauto3", "123456qwert", ERROR_LOGIN_PASSWORD_INCORRECT}
        };
    }

    @Test
    public void validLoginWithButtons() {
        loginPage.openLoginPage();
        loginPage.loginFromKeyboard("qaauto", "123456qwerty");
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void userIsLoggedInNewTab() {
        validLoginWithButtons();
        loginPage.userOpensNewTab();
        loginPage.openLoginPage();
        loginPage.usersPressesKeyEnterTime(1);
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
        homePage.clickOnSignOutButton();
        loginPage.switchToPreviousTabAndRefresh();
        Assert.assertTrue("Button \"Sign in\" is not displayed",
                loginPage.isButtonSignInDisplayed());

    }
}
