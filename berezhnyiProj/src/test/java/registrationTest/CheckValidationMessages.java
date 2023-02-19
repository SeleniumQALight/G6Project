package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CheckValidationMessages extends BaseTest {

    final String USERNAME_ALERT_TITLE = "Username must be at least 3 characters.";
    final String EMAIL_ALERT_TITLE = "You must provide a valid email address.";
    final String PASSWORD_ALERT_TITLE = "Password must be at least 12 characters.";

    @Test
    public void checkRegistrationValidationMessages(){
        loginPage
                .openLoginPage()
                .enterUserNameIntoRegistrationInput("tr")
                .enterEmailIntoRegistrationInput("test.com")
                .enterPasswordIntoRegistrationInput("123")
                .checkTextInAlertMessage(USERNAME_ALERT_TITLE)
                .checkTextInAlertMessage(EMAIL_ALERT_TITLE)
                .checkTextInAlertMessage(PASSWORD_ALERT_TITLE)
        ;
    }
}
