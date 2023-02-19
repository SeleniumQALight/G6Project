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
             .checkAlertMessageQuantity()
             .checkAlertMessageIsOnlyOne("Username must be at least 3 characters.")
             .checkAlertMessageContainText("Username must be at least 3 characters.")
             .checkAlertMessageIsOnlyOne("You must provide a valid email address.")
             .checkAlertMessageContainText("You must provide a valid email address.")
             .checkAlertMessageIsOnlyOne("Password must be at least 12 characters.")
             .checkAlertMessageContainText("Password must be at least 12 characters.")
        ;

    }

}
