package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

@Link("https://example.org")
@Link(name = "allure", type = "mylink")
@Epic("Allure examples")
@Feature("Junit 4 support")
@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String ERROR_INVALID_LOGIN_PASSWORD = "Invalid username pasword";
    //final static String ERROR_INVALID_LOGIN_PASSWORD = "Failed test";  //Failed test
    final static String USERNAME_EXIST_VALID = TestData.VALID_LOGIN;
    final static String USERNAME_EMPTY = "";
    final static String PASSWORD_INVALID = TestData.INVALID_PASSWORD;
    final static String PASSWORD_EMPTY = "";

    @Description("Some detailed test description")
    //@Link("https://example.org")
    //@Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Test
    public void validLogin(){
        loginPage
                .openLoginPage()
                .enterUserNameIntoInputLogin(TestData.VALID_LOGIN)
                .enterPasswordIntoInputPassword(TestData.VALID_PASSWORD)
                .clickOnButtonSignIn()
                .checkIsUserLoggedIn();
    }

    @Test
    public void invalidLogin(){
        loginPage
                .openLoginPage()
                .enterUserNameIntoInputLogin(TestData.VALID_LOGIN)
                .enterPasswordIntoInputPassword(TestData.INVALID_PASSWORD)
                .clickOnButtonSignIn()
                .checkIsUserNotLoggedIn();
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("LoggingInErrors : login = {0}, password = {1}")
    public void checkError(String userName, String password, String expectedError){
        loginPage
                .openLoginPage()
                .enterUserNameIntoInputLogin(userName)
                .enterPasswordIntoInputPassword(password)
                .clickOnButtonSignIn()
                .checkLoggingInErrorMessage(expectedError);
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {USERNAME_EXIST_VALID, PASSWORD_INVALID, ERROR_INVALID_LOGIN_PASSWORD},
                new Object[] {USERNAME_EXIST_VALID, PASSWORD_EMPTY, ERROR_INVALID_LOGIN_PASSWORD},
                new Object[] {USERNAME_EMPTY, PASSWORD_INVALID, ERROR_INVALID_LOGIN_PASSWORD}
        };
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage
                .openLoginPage()
                .enterUserNameIntoInputLogin(dataForValidLogin.get("login"))
                .enterPasswordIntoInputPassword(dataForValidLogin.get("pass"))
                .clickOnButtonSignIn()
                .checkIsUserLoggedIn();

        Assert.assertTrue("Button is not displayed", headerElement.isButtonSignOutDisplayed());
    }
}
