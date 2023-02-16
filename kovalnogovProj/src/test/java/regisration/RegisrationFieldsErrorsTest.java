package regisration;

import baseTest.BaseTest;
import org.junit.Test;

import pages.LoginPage;

public class RegisrationFieldsErrorsTest extends BaseTest {
    public static final String USER_NAME_ERROR_TEXT = "Username must be at least 3 characters.";
    public static final String EMAIL_ERROR_TEXT = "You must provide a valid email address.";
    public static final String PASSWORD_ERROR_TEXT = "Password must be at least 12 characters.";


    @Test
    public void testValidLogin() {

        loginPage.openLoginPage()
                .typeUserNameForRegistration("tr")
                .checkErrorMessageWithText(USER_NAME_ERROR_TEXT, LoginPage.RegistrationFields.USER_NAME)
                .typeEmailForRegistration("test.com")
                .checkErrorMessageWithText(EMAIL_ERROR_TEXT, LoginPage.RegistrationFields.EMAIL)
                .typePasswordForRegistration("123")
                .checkErrorMessageWithText(PASSWORD_ERROR_TEXT, LoginPage.RegistrationFields.PASSWORD)
        ;


    }
}
