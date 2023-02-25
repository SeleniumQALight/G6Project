package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.Util;
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
    final static String USERNAME_TO_SHORT = "vt";
    final static String USERNAME_TO_LONG = "31CharactersUserNameQwertyuiopa";
    final static String USERNAME_INVALID_CHARACTER = "КирилицаЛогин";
    final static String USERNAME_HAS_SYMBOL = "UserName!@#$%^&";
    final static String EMAIL_INVALID = "www";
    final static String PASSWORD_TO_SHORT = "asd";
    final static String PASSWORD_TO_LONG = "51CharactersUserPasswordQwertyuiopasdfghjklzxcvbn51";
    final static String VALID_RANDOM_REGISTRATION_USER_NAME = "username" + Util.getDateAndTimeFormattedOnlyNumbers();
    final static String VALID_RANDOM_REGISTRATION_EMAIL = "email" + Util.getDateAndTimeFormattedOnlyNumbers() + "@test.com";
    final static String VALID_RANDOM_REGISTRATION_PASSWORD = "password" + Util.getDateAndTimeFormattedOnlyNumbers();
    final static String SEMICOLON = ";";
    final static String COMMA = ",";


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
                new Object[] {USERNAME_TO_SHORT, EMAIL_INVALID, PASSWORD_TO_SHORT, ERROR_USERNAME_SHORT + COMMA + ERROR_EMAIL_INVALID + COMMA + ERROR_PASSWORD_SHORT},
                new Object[] {VALID_RANDOM_REGISTRATION_USER_NAME, VALID_RANDOM_REGISTRATION_EMAIL, PASSWORD_TO_SHORT, ERROR_PASSWORD_SHORT},
                new Object[] {USERNAME_INVALID_CHARACTER, VALID_RANDOM_REGISTRATION_EMAIL, VALID_RANDOM_REGISTRATION_PASSWORD, ERROR_USERNAME_INVALID_CHARACTER},
                new Object[] {USERNAME_HAS_SYMBOL, VALID_RANDOM_REGISTRATION_EMAIL, VALID_RANDOM_REGISTRATION_PASSWORD, ERROR_USERNAME_INVALID_CHARACTER},
                new Object[] {USERNAME_TO_LONG, VALID_RANDOM_REGISTRATION_EMAIL, PASSWORD_TO_LONG, ERROR_USERNAME_LONG + COMMA + ERROR_PASSWORD_LONG}
        };
    }
}
