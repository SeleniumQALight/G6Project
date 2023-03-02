package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class InvalidLoginWithParams extends BaseTest {
    final static String ERROR_USERNAME_PASSWORD = "Invalid username pasword";

    //String expectedErrors = ERROR_USERNAME_PASSWORD;

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, password = {1}")
    public void checkLoginErrors(String userName, String password, String expectedErrors){
        loginPage
                .openLoginPage()
                .enterUserNameIntoInputLogin(userName)
                .enterPasswordIntoInputPassword(password)
                .clickOnButtonLogin()
                .checkErrorMessagesOnLogin(expectedErrors)
                ;
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"qwq", "asdfghj", ERROR_USERNAME_PASSWORD},
                new Object[] {"qaauto", "", ERROR_USERNAME_PASSWORD},
                new Object[] {"random", "123456qwerty", ERROR_USERNAME_PASSWORD}
        };
    }

}
