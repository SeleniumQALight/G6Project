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
                .typeUserNameForRegistration("val")
                .typeEmailForRegistration("test.com")
                .typePasswordForRegistration("123")
                .checkErrorMessageWithText(USER_NAME_ERROR_TEXT)
                .checkErrorMessageWithText(EMAIL_ERROR_TEXT)
                .checkErrorMessageWithText(PASSWORD_ERROR_TEXT);


    }
}
