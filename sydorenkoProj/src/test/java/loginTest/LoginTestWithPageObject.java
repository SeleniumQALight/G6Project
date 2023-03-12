package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pages.CommonActionsWithElements.configProperties;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {

    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }
    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void validLoginWithButtons() {
        loginPage.openLoginPage();
        loginPage.usersPressesKeyTabTime(2);
        loginPage.actionsSendKeys("qaauto");
        loginPage.usersPressesKeyTabTime(1);
        loginPage.actionsSendKeys("123456qwerty");
        loginPage.usersPressesKeyTabTime(1);
        loginPage.usersPressesKeyEnterTime(1);

        assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void isLoginValidInNewTab() {
        loginPage.openLoginPage();
        loginPage.usersPressesKeyTabTime(2);
        loginPage.actionsSendKeys("qaauto");
        loginPage.usersPressesKeyTabTime(1);
        loginPage.actionsSendKeys("123456qwerty");
        loginPage.usersPressesKeyTabTime(1);
        loginPage.usersPressesKeyEnterTime(1);
        loginPage.openNewTabWithSameUrl();
        loginPage.openLoginPage();

        assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

        homePage.getHeaderElement().clickOnSignOutButton();
        loginPage.switchToFirstTabAndRefresh();

        assertTrue("Button is not displayed", loginPage.isButtonSignInDisplayed());
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
