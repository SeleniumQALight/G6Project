package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class FormRegistrationTest extends BaseTest {
    final String USER_NAME = "1";
    final String EMAIL = "12.1";
    final String PASSWORD = "2";
    @Test
    public void checkInvalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputRegisteredForm(USER_NAME);
        loginPage.enterEmailIntoInputRegistered(EMAIL);
        loginPage.enterPasswordIntoInputRegisteredForm(PASSWORD);
        loginPage.checkAlertMessageWithText()
                .checkErrorMessageWithText("Username must be at least 3 characters.")
                .checkErrorMessageWithText("You must provide a valid email address.")
                .checkErrorMessageWithText("Password must be at least 12 characters.")
                ;
    }

}
