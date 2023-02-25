package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class RegistrationErrorsTest extends BaseTest {
    final static String ERROR_USERNAME_SHORT = "Username must be at least 3 characters.";
    final static String ERROR_USERNAME_LONG = "Username cannot exceed 30 characters.";
    final static String ERROR_USERNAME_INVALID_CHARACTER = "Username can only contain letters and numbers.";
    final static String ERROR_EMAIL_INVALID = "You must provide a valid email address.";
    final static String ERROR_PASSWORD_SHORT = "Password must be at least 12 characters.";
    final static String ERROR_PASSWORD_LONG = "Password cannot exceed 50 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    String expectedErrors = ERROR_USERNAME_SHORT + COMMA + ERROR_EMAIL_INVALID + COMMA + ERROR_PASSWORD_SHORT;

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrors(String userName, String email, String password, String expectedErrors){
        loginPage
                .openLoginPage();
        loginPage
                .enterUserNameIntoInputRegistrationUserName(userName)
                .enterEmailIntoInputRegistrationEmail(email)
                .enterPasswordIntoInputRegistrationPassword(password)
                .checkErrorsMessages(expectedErrors);
    }


    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "ttt", "ttt", ERROR_USERNAME_SHORT + COMMA + ERROR_EMAIL_INVALID + COMMA + ERROR_PASSWORD_SHORT},
                new Object[] {"un", "email@test.com", "ppp", ERROR_USERNAME_SHORT + COMMA + ERROR_PASSWORD_SHORT},
                new Object[] {"КирилицаЛогин", "email@test.com", "Password", ERROR_USERNAME_INVALID_CHARACTER},
                new Object[] {"UserName!@#$%^&", "email@test.com", "Password", ERROR_USERNAME_INVALID_CHARACTER},
                new Object[] {"31CharactersUserNameQwertyuiopa", "email@test.com", "51CharactersUserPasswordQwertyuiopasdfghjklzxcvbn51", ERROR_USERNAME_LONG + COMMA + ERROR_PASSWORD_LONG}
        };
    }
}
