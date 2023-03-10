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
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";



    String expectedErrors=ERROR_USERNAME+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD;

    final static String ERROR_USERNAME_WITH_TYPE_VALUE="Username can only contain letters and numbers.";



    final static String ERROR_LOGIN="Invalid username pasword";


    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrors(String userName, String email, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage
                .enterUserNameIntoInputUserNameForRegister(userName)
                .enterEmailIntoInputEmailForRegister(email)
                .enterPasswordIntoInputPasswordForRegister(password)
                .checkSizeOfErrorsList(3)
                .checkErrorsMessages(expectedErrors);
    }


    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "ttt", "ttt", ERROR_USERNAME+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD},
                new Object[] {"tr", "tt3t", "t3tt", ERROR_USERNAME+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD},
                new Object[] {"1", "2", "3", ERROR_USERNAME+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD},
               //new Object[] {"qqq", "2", "3", ERROR_USERNAME+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD},
               // new Object[] {"qq", "test@gmail.com", "3", ERROR_USERNAME+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD},
               // new Object[] {"qq", "2", "Test12345678", ERROR_USERNAME+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD},
                new Object[] {"тест", "2", "3", ERROR_USERNAME_WITH_TYPE_VALUE+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD},
        };
    }


    @Test
    @Parameters(method = "provideParameters2")
    @TestCaseName("registrationErrors : login = {0}, password = {1}")
    public void checkErrorsForLogin(String userName,  String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(userName);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();
        loginPage.checkLoginError();
        loginPage.checkErrorsMessageForLogIn();

    }


    public static Object[][] provideParameters2() {
        return new Object[][] {
                new Object[] {"tr", "ttt"},
                new Object[] {"123", "123456qwerty"},
                new Object[] {"qaauto", "3"},
        };
    }



}
