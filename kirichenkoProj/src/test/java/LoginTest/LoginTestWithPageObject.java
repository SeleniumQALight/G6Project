package LoginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String LOGIN_ERROR_MESSAGE = "Invalid username pasword";

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed"
                , headerElement.isButtonSignOutDisplayed());
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("loginErrors : login = {0}, password = {1}")
    public void invalidLogin(String userName, String password) {
        loginPage.fillingLoginFormWithInvalidCred(userName, password);
        loginPage.checkTextInLoginErrorMessage(LOGIN_ERROR_MESSAGE);

        Assert.assertFalse("Home page is loaded", headerElement.isButtonSignOutDisplayed());
        Assert.assertTrue("Sign in button is not displayed", loginPage.isSignInButtonDisplayed());
    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"", ""},
                new Object[]{"nnnn", "123456qwerty"},
                new Object[]{"qaauto", "ytytyt"}
        };
    }


    @Test
    public void validLoginWithButtons(){
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
                loginPage.isSignInButtonDisplayed());
    }
}
