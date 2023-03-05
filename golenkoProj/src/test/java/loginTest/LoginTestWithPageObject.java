package loginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;


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
    public void invalidLoginWithParams(String login, String password) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(login);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();
        loginPage.checkErrorMessageForLogin(ERROR_LOGIN_PASSWORD_INCORRECT);

    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"", "123456qwerty"},
                new Object[]{"qaauto", ""},
                new Object[]{"qaauto3", "123456qwert"}
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

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());

    }
}
