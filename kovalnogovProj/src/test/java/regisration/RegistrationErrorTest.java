package regisration;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrationErrorTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    // String expectedErrors = ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD;

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrors(String userName, String userEmail, String password, String expectedErrors) {
        loginPage.openLoginPage()
                .typeUserNameForRegistration(userName)
                .typeEmailForRegistration(userEmail)
                .typePasswordForRegistration(password)
                .checkErrorMessageUniversal(expectedErrors);
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tt","ttt","ttt",ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD },
                new Object[] {"tt","tt@t","ttt", ERROR_USERNAME + COMMA + ERROR_PASSWORD},
                new Object[] {"newUserName","tt@test.com","tt",  ERROR_PASSWORD},
                new Object[] {"newUserName","test.com","tt", ERROR_EMAIL + COMMA + ERROR_PASSWORD}
        };
    }

}
