package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
@Parameters(method = "provideParameters")
public class InvalidLoginWithParameters extends BaseTest {
    final static String ERROR_LOGIN_PASSWORD = "Invalid username pasword";




   @Test
   @Parameters(method = "provideParameters")

    public void invalidLoginWithParameters(String login, String password, String expectedError) {
       loginPage.openLoginPage();
       loginPage.enterUserNameIntoInputLogin(login);
       loginPage.enterPasswordIntoInputPassword(password);
       loginPage.clickButtonLogin();
       loginPage.checkErrorMessageInvalidLogin(expectedError);
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"qw", "111", ERROR_LOGIN_PASSWORD },
                new Object[] {"qaauto", "222", ERROR_LOGIN_PASSWORD},
                new Object[] {"as", "qwerty123456", ERROR_LOGIN_PASSWORD},
        };

    }



}
