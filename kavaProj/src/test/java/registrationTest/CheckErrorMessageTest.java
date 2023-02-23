package registrationTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class CheckErrorMessageTest extends BaseTest {

    @Test
    public void errorMessageTest() {
        loginPage.openLoginPage();
        loginPage.enterUserNameToTheRegistrationField("tr");
        loginPage.enterEmailToTheRegistrationField("test.com");
        loginPage.enterPasswordToTheRegistrationField("123");
        Assert.assertTrue("Username element is not displayed",
                loginPage.checkErrorMessageWithText("Username must be at least 3 characters."));
        Assert.assertTrue("Email element is not displayed",
                loginPage.checkErrorMessageWithText("You must provide a valid email address."));
        Assert.assertTrue("Password element is not displayed",
                loginPage.checkErrorMessageWithText("Password must be at least 12 characters."));

    }


}
