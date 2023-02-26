package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class AlertRegistrationTabKeyTest extends BaseTest {
    final String USER_NAME = "1";
    final String EMAIL = "12.1";
    final String PASSWORD = "2";
    @Test
    public void invalidAlertRegistrationTabKey(){
        loginPage.openLoginPage();
        loginPage.registrationUserNameTabKey(5,USER_NAME);
        loginPage.registrationEmailTabKey(EMAIL);
        loginPage.registrationPasswordTabKey(PASSWORD);
        loginPage.checkAlertMessageWithText(3)
                .checkErrorMessageWithText("Username must be at least 3 characters.")
                .checkErrorMessageWithText("You must provide a valid email address.")
                .checkErrorMessageWithText("Password must be at least 12 characters.")
        ;

    }
}
