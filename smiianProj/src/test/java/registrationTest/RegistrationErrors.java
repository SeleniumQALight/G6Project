package registrationTest;

import BaseTest.BaseTest;
import junitparams.JUnitParamsRunner;  // додали бібліотеку
// + не забути дані з Pom-файлу <dependency> <groupId>pl.pragmatists</groupId>
// <artifactId>JUnitParams</artifactId> <version>1.0.5</version> <scope>test</scope> </dependency>
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)   // дозволяє використовувати список з різними списками даних

public class RegistrationErrors extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_USERNAME_CONTAIN = "Username can only contain letters and numbers.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";

    String expectedErrors = ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD;

    @Test
    @Parameters(method = "provideParameters")    // анотація вказує де саме тесту брати варанти з параметрами
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")   // форматує повідомлення яке виводиться у вікні Run
    public void checkErrors(String userName, String mail, String password, String expectedErrors) {
        loginPage
                .openLoginPage();
        loginPage.enterDataIntoUsernameField(userName)
                .enterDataIntoEmailField(mail)
                .enterDataIntoPasswordField(password)
                .checkErrorsMessages(expectedErrors)
        ;
    }
    public static Object[][] provideParameters() {                                      // варіанти різних наборів даних
        return new Object[][] {
                new Object[] {"tr", "ttt", "ttt", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"tr", "tt@tr.com", "ttt", ERROR_USERNAME + COMMA + ERROR_PASSWORD},
                new Object[] {"іва", "tt@tr.com", "ttt", ERROR_USERNAME_CONTAIN + COMMA + ERROR_PASSWORD},  // not valid char's in username field
                new Object[] {"tr", "tt@tr.com", "123456789012", ERROR_USERNAME}   // 12 char's in password field
        };
    }


}