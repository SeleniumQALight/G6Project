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

    public void invalidLoginWithParameters(String login, String password) {
       loginPage.openLoginPage();
       loginPage.enterUserNameIntoInputLogin(login);
       loginPage.enterPasswordIntoInputPassword(password);
       loginPage.clickButtonLogin();
       Assert.assertTrue("User is logged in with incorrect credentials", (loginPage.isErrorMessageDisplayed()) & (loginPage.isSignInButtonDisplayed()));
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"qw", "111" },
                new Object[] {"qaauto", "222"},
                new Object[] {"as", "qwerty123456"},
        };

    }



}
