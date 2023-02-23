package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class CheckErrorMessageTest extends BaseTest {

    @Test
    public void errorMessageTest() {
        loginPage.openLoginPage();
        loginPage.enterUserNameToTheRegistrationField("tr");
        loginPage.checkErrorMessageWithText("Username must be at least 3 characters.");
        loginPage.enterEmailToTheRegistrationField("test.com");
        loginPage.checkErrorMessageWithText("You must provide a valid email address.");
        loginPage.enterPasswordToTheRegistrationField("123");
        loginPage.checkErrorMessageWithText("Password must be at least 12 characters.");


    }


}
