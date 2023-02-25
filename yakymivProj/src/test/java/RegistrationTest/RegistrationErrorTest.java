package RegistrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrationErrorTest extends BaseTest{
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_USERNAME_SMALL = "Username must be at least 3 characters.";
    final static String ERROR_PASSWORD_SMALL = "Password must be at least 12 characters.";
    final static String ERROR_USERNAME_BIG = "Username cannot exceed 30 characters.";
    final static String ERROR_PASSWORD_BIG = "Password cannot exceed 50 characters.";
    final static String ERROR_USERNAME_SYMBOLS = "Username can only contain letters and numbers.";
    final static String ERROR_ALREADY_EXIST = "This email is already being used.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

//    String expectedErrors = ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD;

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrors(String userName,String email, String password, String expectedErrors){
        loginPage
                .openLoginPage();
        loginPage.enterUserNameintoRegistrationForm(userName)
                .enterEmailInRegistrationForm(email)
                .enterPasswordInRegistrationForm(password)
                .checkErrorMessage(expectedErrors);
        ;
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr","ttt","ttt", ERROR_USERNAME_SMALL + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD_SMALL},
                new Object[] {"tr","tt@tt.com","ttt", ERROR_USERNAME_SMALL + COMMA + ERROR_PASSWORD_SMALL},
                new Object[] {"31symbols_sfsdfsdfsdfsdfsdfdfs1","yakymiv.ivan@gmail.com","normal_password", ERROR_USERNAME_BIG + COMMA + ERROR_ALREADY_EXIST},
                new Object[] {"!\"\"№\"","tt@tt.com","51symbols_sfsdfsdfsdfsdfsdfdfsdsfsdfsdfsdfsdfsdfdfs", ERROR_USERNAME_SYMBOLS + COMMA + ERROR_PASSWORD_BIG}
        };
    }
}
