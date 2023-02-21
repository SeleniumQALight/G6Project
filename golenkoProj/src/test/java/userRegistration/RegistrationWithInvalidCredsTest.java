package userRegistration;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class RegistrationWithInvalidCredsTest extends BaseTest {

    @Test
    public void TC2_checkErrorMessages(){
        loginPage
                .openLoginPage()
                .enterUserNameIntoInputSignUp(TestData.INVALID_USERNAME)
                .enterEmailIntoInputSignUp(TestData.INVALID_EMAIL)
                .enterPasswordIntoInputSignUp(TestData.INVALID_PASSWORD)
                .checkThreeAlertMassagesAreDisplayed()
                .checkErrorMessageWithText("Username must be at least 3 characters.")
                .checkErrorMessageWithText("You must provide a valid email address.")
                .checkErrorMessageWithText("Password must be at least 12 characters.")
        ;
    }




}
