package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static libs.TestData.VALID_LOGIN;
import static libs.TestData.VALID_PASSWORD;
import static pages.CommonActionsWithElements.configProperties;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {


    final static String ERROR_LOGIN_MESSAGE = "Invalid username pasword";

    @Test
//    @Ignore
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(VALID_LOGIN);
        loginPage.enterPaswordIntoInputPassword(VALID_PASSWORD);
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void notValidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(VALID_LOGIN);
        loginPage.enterPaswordIntoInputPassword("123456qwerty1");
        loginPage.clickOnButtonLogin();

        Assert.assertFalse("Button SignOut is displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button SignIn is displayed", loginPage.isButtonSignInDisplayed());

    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPaswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("logInError : login = {0}, password = {1}")
    public void notValidLoginWithParameters(String userName, String password, String expectedMessage) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(userName);
        loginPage.enterPaswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();
        loginPage.checkErrorMessageForInvalidLogin(expectedMessage);
    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"tr", "123456qwerty", ERROR_LOGIN_MESSAGE},
                new Object[]{"qaauto", "123", ERROR_LOGIN_MESSAGE},
                new Object[]{"", "", ERROR_LOGIN_MESSAGE}
        };
    }


}