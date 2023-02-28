package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String ERROR_INVALID_LOGIN_PASSWORD = "Invalid username pasword";
    final static String USERNAME_EXIST_VALID = TestData.VALID_LOGIN;
    final static String USERNAME_EMPTY = "";
    final static String PASSWORD_INVALID = TestData.INVALID_PASSWORD;
    final static String PASSWORD_EMPTY = "";

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
}
