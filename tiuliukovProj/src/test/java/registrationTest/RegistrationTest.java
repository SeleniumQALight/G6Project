package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    final String REGISTRATION_NAME = "tr";
    final String REGISTRATION_EMAIL = "test.com";
    final String REGISTRATION_PASSWORD = "123";
    final String INVALID_USERNAME_MESSAGE = "Username must be at least 3 characters.";
    final String INVALID_EMAIL_MESSAGE = "You must provide a valid email address.";
    final String INVALID_PASSWORD_MESSAGE = "You must provide a valid email address.";

    @Test
    public void registrationValidationFieldsTest() {
        loginPage.openLoginPage()
                .enterUserNameIntoInputRegistrationUserName(REGISTRATION_NAME)
                .enterEmailIntoInputRegistrationEmail(REGISTRATION_EMAIL)
                .enterPasswordIntoInputRegistrationPassword(REGISTRATION_PASSWORD)
                .checkIsValidationMessageDisplayed(INVALID_USERNAME_MESSAGE)
                .checkIsValidationMessageDisplayed(INVALID_EMAIL_MESSAGE)
                .checkIsValidationMessageDisplayed(INVALID_PASSWORD_MESSAGE)
                .checkValidationMessagesAmount();
    }
}
