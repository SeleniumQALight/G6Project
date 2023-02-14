package signUpForm;

import BaseTest.BaseTest;
import org.junit.Test;

public class NotValidDataMessagesCheck extends BaseTest{
    final String USER_NAME = "tr";
    final String EMAIL = "test.com";
    final String PASSWORD = "123";

    @Test
    public void TC2_notValidMessagesCheck() {
        loginPage
                .openLoginPage()
                .enterDataIntoUsernameField(USER_NAME)
                .enterDataIntoEmailField(EMAIL)
                .enterDataIntoPasswordField(PASSWORD)
             .checkAlertMessage("Username must be at least 3 characters.")
             .checkIsUsernameAlertMessageContainText("Username must be at least 3 characters.")
             .checkAlertMessage("You must provide a valid email address.")
             .checkIsEmailAlertMessageContainText("You must provide a valid email address.")
             .checkAlertMessage("Password must be at least 12 characters.")
             .checkIsPasswordAlertMessageContainText("Password must be at least 12 characters.")
        ;

    }

}
