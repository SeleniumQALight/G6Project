package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void invalidRegistration() {
        loginPage.openLoginPage();
        loginPage.enterNameIntoInputUserNameRegister("1");
        loginPage.enterEmailIntoFormRegister("em");
        loginPage.enterPasswordIntoFormRegister("123");
        loginPage.checkIsThreeErrorMessagesDisplayed();
        loginPage.checkErrorMessageWithTextByParamLocator("Username must be at least 3 characters.");
        loginPage.checkErrorMessageWithTextByParamLocator("You must provide a valid email address.");
        loginPage.checkErrorMessageWithTextByParamLocator("Password must be at least 12 characters.");
    }
}
