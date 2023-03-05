package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrationErrorsTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    final static String USERNAME_CYRILLIC = "тест";
    final static String PASSWORD_MAX = "60characterCharacter60characterCharacter60characterCharacter";
    final static String EMAIL_INVALID = "test";
    final static String ERROR_USERNAME_INVALID_CYRILLIC = "Username can only contain letters and numbers.";
    final static String ERROR_PASSWORD_MAX = "Password cannot exceed 50 characters.";
    final static String ERROR_EMAIL_INVALID = "You must provide a valid email address.";


//    String expectedErrors = ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD;


    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrors(String userName, String email, String password, String expectedErrors){
        loginPage
                 .openLoginPage();
        loginPage.enterUserNameInRegistrationForm(userName)
                 .enterEmailInRegistrationForm(email)
                 .enterPasswordInRegistrationForm(password)
                 .checkErrorsMessages(expectedErrors);
    }
    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "ttt", "ttt", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"tr", "tt@tt.com", "ttt", ERROR_USERNAME + COMMA + ERROR_PASSWORD},
                new Object[]{USERNAME_CYRILLIC, EMAIL_INVALID, PASSWORD_MAX, ERROR_USERNAME_INVALID_CYRILLIC + COMMA + ERROR_EMAIL_INVALID + COMMA + ERROR_PASSWORD_MAX}
        };

    }
    @Test
    public void TC2_checkErrorMessages(){
        loginPage
                 .openLoginPage();
        loginPage.enterUserNameInRegistrationForm(TestData.INVALID_USERNAME)
                 .enterEmailInRegistrationForm(TestData.INVALID_EMAIL)
                 .enterPasswordInRegistrationForm(TestData.INVALID_PASSWORD)
                 .checkThreeAlertMessagesAreDisplayed()
                 .checkErrorMessageWithText(ERROR_USERNAME)
                 .checkErrorMessageWithText(ERROR_EMAIL)
                 .checkErrorMessageWithText(ERROR_PASSWORD);
    }
}
