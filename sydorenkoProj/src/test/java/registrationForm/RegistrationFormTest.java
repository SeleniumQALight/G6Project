package registrationForm;

import baseTest.BaseTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegistrationFormTest extends BaseTest {
    @Test
    public void inValidRegistration() {
        loginPage.openLoginPage();
        loginPage.enterNameIntoNameRegisterField("tr");
        loginPage.enterEmailIntoEmailRegisterField("test.com");
        loginPage.enterPasswordIntoPasswordRegisterField("123");
        assertTrue("Name field validation error isn't displayed", loginPage.isFieldValidationErrorDisplayed("Username must be at least 3 characters."));
        assertTrue("Email field validation error isn't displayed", loginPage.isFieldValidationErrorDisplayed("You must provide a valid email address."));
        assertTrue("Password field validation error isn't displayed", loginPage.isFieldValidationErrorDisplayed("Password must be at least 12 characters."));
    }
}
