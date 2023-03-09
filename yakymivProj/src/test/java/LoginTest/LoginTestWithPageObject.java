package LoginTest;

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

import static pages.CommonActionsWithElements.configProperties;

@Epic("Allure examples")
@Feature("Junit 4 support")
@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String ERROR_MESSAGE = "Invalid username pasword";

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
        loginPage.enterUserNameintoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("invalidLogin : login = {0}, password = {1}")
    public void invalidLogin(String userName, String password, String expectedError) {
        loginPage
                .openLoginPage()
                .fillingLoginFormWithInvalidCred(userName, password)
                .checkLogInErrorMessage(expectedError);
    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"ivan19", "invalid_pass", ERROR_MESSAGE},
                new Object[]{"invalid_userName", "valid_pass", ERROR_MESSAGE},
                new Object[]{"invalid_userName", "invalid_pass", ERROR_MESSAGE}
                //homework pt2
        };
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameintoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }
}
