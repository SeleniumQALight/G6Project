package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateTestForValidationMessages extends BaseTest {
    public final static String INVALID_PASS = "123";
    public final static String INVALID_EMAIL = "test.com";
    public final static String INVALID_USERNAME = "tr";

    @Test
    public void TC2_verifyValidatinMessages(){
        loginPage.openLoginPage()
                .enterTextInPickAUsername(INVALID_USERNAME)
                .enterTextInYourEmailExpample(INVALID_EMAIL)
                .enterTextInCreatePass(INVALID_PASS)
                .checkErrorMessagesIsDisplayed(3)
                .checkErrorMessageWithText("Username must be at least 3 characters.")
                .checkErrorMessageWithText("You must provide a valid email address.")
                .checkErrorMessageWithText("Password must be at least 12 characters.")
        ;


////*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']









    }







}
