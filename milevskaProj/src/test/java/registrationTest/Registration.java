package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class Registration extends BaseTest {

    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    String expectedErrors = ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD;
    @Test
    @Parameters(method = "provideParameters")
    public void checkErrors(String userName, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage.enterUsernameInRegistrationForm(userName)
                .enterEmailInRegistrationForm(email)
                .enterPassInRegistrationForm(password)
                .checkErrorsMessages(expectedErrors);
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "ttt", "rrr", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"tr", "t@tt.com", "rrr", ERROR_USERNAME + COMMA + ERROR_PASSWORD}
        };
    }


}
