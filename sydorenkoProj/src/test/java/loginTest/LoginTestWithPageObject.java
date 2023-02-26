package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void inValidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("1123456qwerty");
        loginPage.clickOnButtonLogin();

        assertFalse("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
        assertTrue("Button is not displayed", loginPage.isButtonSignInDisplayed());
        assertTrue("Button is not displayed", (!homePage.getHeaderElement().isButtonSignOutDisplayed() & loginPage.isButtonSignInDisplayed()));
    }
    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("inValidLoginParameters: login = {0}, password = {1}")
    public void inValidLoginWithParameters(String login, String password) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(login);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();

        assertFalse("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
        assertTrue("Button is not displayed", loginPage.isButtonSignInDisplayed());
        assertTrue("Button is not displayed", (!homePage.getHeaderElement().isButtonSignOutDisplayed() & loginPage.isButtonSignInDisplayed()));
    }
    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"qaauto", "1123456qwerty"},
                new Object[]{"qaautoo", "1123456qwertyy"},
        };
    }

}
