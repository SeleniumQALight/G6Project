package registrationTest;

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

        final static String ERROR_EMAIL = "You must provide a valid email address.";

        final static String ERROR_PASSWORD = "Password must be at least 12 characters.";

        final static String ERROR_ALREADY_EXIST = "That username is already taken.";

        final static String ERROR_USERNAME_NOT_VALID = "Username can only contain letters and numbers.";

        final static String SEMICOLON = ";";

        final static String COMMA = ",";

        final static String SHORT_USER_NAME = "tr";

        //String expectedErrors = ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD;


        @Test
        @Parameters(method = "provideParameters")
        @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")



        public void checkErrors(String username, String email, String password, String expectedErrors) {
            loginPage
                    .openLoginPage()
                    .enterUserNameIntoInputSignUp(username)
                    .enterEmailIntoInputSignUp(email)
                    .enterPasswordInputSignUp(password)
                    .checkErrorMessages(expectedErrors)
            ;


        }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "ttt", "ttt", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"tr", "t@tt.com", "ttt", ERROR_USERNAME + COMMA + ERROR_PASSWORD},
                new Object[] {"test", "t", "123", ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"тест", "tt", "asdfgh123456", ERROR_USERNAME_NOT_VALID + COMMA + ERROR_EMAIL}

        };

    }

    }

