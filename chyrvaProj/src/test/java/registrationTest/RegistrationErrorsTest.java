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
final static String ERROR_USERNAME_OTHER_SYMBOLS = "Username can only contain letters and numbers.";
    final static String ERROR_PASSWORD_OVER_NUMBER_CHAR = "Password cannot exceed 50 characters.";
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
    public void checkErrors(String userName, String email, String password, String expectedErrors) {
        loginPage
                .openLoginPage();
        loginPage.enterUserNameInRegistrationForm(userName)
                .enterEmailInRegistrationForm(email)
                .enterPasswordInRegistrationForm(password)
                .checkErrorMessages(expectedErrors);

    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"tr", "ttt", "ttt", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[]{"tr", "tt@tt.com", "ttt", ERROR_USERNAME + COMMA + ERROR_PASSWORD},
                new Object[]{"воот","tt@tt.com","qwertyuioplkjhgfddsazxcvbnm,./lkjhgfdsaqwertyuioplkj", ERROR_USERNAME_OTHER_SYMBOLS + COMMA + ERROR_PASSWORD_OVER_NUMBER_CHAR},
               new Object[]{"воот", "tt@tt.com", "werrtgttrfjfghjgj", ERROR_USERNAME_OTHER_SYMBOLS }

        };
    }

}
//1. Дописати два набора данних в ктой кейс шо ми написали
//        (бажано щоб хоч один месседж був іший, а не той що ми використали,
//        наприклад логін ввести кіріліцею "тест" або пароль більший за 60 символів)
//        2. Написати тест на невалідний логін з декількама наборами данних