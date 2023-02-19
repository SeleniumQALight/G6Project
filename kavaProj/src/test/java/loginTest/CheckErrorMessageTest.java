package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CheckErrorMessageTest extends BaseTest {

    @Test
    public void usernameErrorMessageTest() {
        loginPage.openLoginPage();
        loginPage.enterUserNameToTheRegistrationField("tr")
                 .checkErrorMessageWithText("Username must be at least 3 characters.");

    }

    @Test
    public void emailErrorMessageTest() {
        loginPage.openLoginPage();
        loginPage.enterEmailToTheRegistrationField("test.com")
                 .checkErrorMessageWithText("You must provide a valid email address.");

    }

    @Test
    public void passwordErrorMessageTest() {
        loginPage.openLoginPage();
        loginPage.enterPasswordToTheRegistrationField("123")
                 .checkErrorMessageWithText("Password must be at least 12 characters.");
    }
}
