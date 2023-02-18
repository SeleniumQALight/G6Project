package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CheckErrorMessageTest extends BaseTest {

    @Test
    public void errorMessageTest() {
        loginPage.openLoginPage();
        loginPage.enterUserNameToTheRegistrationField("tr")
                .checkErrorMessageWithText("Username must be at least 3 characters.")
                .enterEmailToTheRegistrationField("test.com")
                .checkErrorMessageWithText("You must provide a valid email address.")
                .enterPasswordToTheRegistrationField("123")
                .checkErrorMessageWithText("Password must be at least 12 characters.");


    }
}
