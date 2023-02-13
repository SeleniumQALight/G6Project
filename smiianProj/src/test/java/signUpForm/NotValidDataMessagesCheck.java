package signUpForm;

import BaseTest.BaseTest;
import libs.Util;
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

        ;

    }

}
