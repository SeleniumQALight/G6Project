package registrationForm;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class RegistrationErrorsTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_USERNAME_WRONG_SYMBOLS = "Username can only contain letters and numbers.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_PASSWORD_EXCEEDING_LIMIT = "Password cannot exceed 50 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";
//    String expectedErrors = ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD;

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrors(String userName, String email, String password, String expectedErrors) {
        loginPage.openLoginPage();
        loginPage.enterNameIntoNameRegisterField(userName);
        loginPage.enterEmailIntoEmailRegisterField(email);
        loginPage.enterPasswordIntoPasswordRegisterField(password);
        loginPage.checkErrorsMessages(expectedErrors);
    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"tr", "ttt", "ttt", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[]{"tr", "tt@tt.com", "ttt", ERROR_USERNAME + COMMA + ERROR_PASSWORD},
                new Object[]{"логін", "tt@tt.com", "ttt", ERROR_USERNAME_WRONG_SYMBOLS + COMMA + ERROR_PASSWORD},
                new Object[]{"tr", "tt@tt.com", "123456789012345678901234567890123456789012345678901234567890", ERROR_USERNAME + COMMA + ERROR_PASSWORD_EXCEEDING_LIMIT},
        };
    }
}
