package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class AssertionTest extends BaseTest {
    final static String USERNAME_ASSERTNAME = "username-register";
    final static String EMAIL_ASSERTNAME = "email-register";
    final static String PASS_ASSERTNAME = "password-register";
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";

    @Test

    public void assertionCheck(){
        loginPage.openLoginPage();
        loginPage.enterUsernameInRegistrationForm(TestData.INVALID_USERNAME)
                .checkIsAssertionDisplayed(USERNAME_ASSERTNAME,ERROR_USERNAME)
                .enterEmailInRegistrationForm(TestData.INVALID_EMAIL)
                .checkIsAssertionDisplayed(EMAIL_ASSERTNAME,ERROR_EMAIL)
                .enterPassInRegistrationForm(TestData.INVALID_PASSWORD)
                .checkIsAssertionDisplayed(PASS_ASSERTNAME,ERROR_PASSWORD)
                .checkIsAllAssertionDisplayed(3);
    }
}
