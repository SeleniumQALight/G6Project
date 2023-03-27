package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class registrationErrorsTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_USERNAME_EXCEED = "Username cannot exceed 30 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_PASSWORD_EXCEED = "Password cannot exceed 50 characters.";
    final static String ERROR_ALREADY_EXIST = "This username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    String expectedErrors = ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD;
    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName ("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrors(String userName, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoSignUpForm(userName);
        loginPage.enterEmailIntoSignUpForm(email);
        loginPage.enterPasswordIntoSignUpForm(password);
        loginPage.checkErrorsMessages(expectedErrors)
                ;
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "ttt", "ttt", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"tr", "tt@t", "ttt", ERROR_USERNAME + COMMA + ERROR_PASSWORD},
                new Object[] {"qaauto", "tt", "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt",
                ERROR_ALREADY_EXIST + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD_EXCEED},
                new Object[] {"rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "tt@t", "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
                ERROR_USERNAME_EXCEED}
        };
    }
}
