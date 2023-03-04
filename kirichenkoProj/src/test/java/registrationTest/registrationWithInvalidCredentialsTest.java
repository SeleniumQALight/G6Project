package registrationTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class registrationWithInvalidCredentialsTest extends BaseTest {

    @Test
    public void TC2_CheckRegistrationValidationMessages (){
        loginPage
                .fillingRegistrationFormFields()
                .checkValidationMassagesDisplayed()
                . checkErrorMessageText("Username must be at least 3 characters.")
                .checkErrorMessageText("You must provide a valid email address.")
                .checkErrorMessageText("Password must be at least 12 characters.");
    }

    @Test
    public void checkErrorMessagesUsingButtons(){
        loginPage.registrationFromKeyBoard(TestData.REGISTRATION_INVALID_USERNAME, TestData.REGISTRATION_INVALID_EMAIL, TestData.REGISTRATION_INVALID_PASSWORD)
                .checkValidationMassagesDisplayed()
                .checkErrorMessageText("Username must be at least 3 characters.")
                .checkErrorMessageText("You must provide a valid email address.")
                .checkErrorMessageText("Password must be at least 12 characters.");
                ;
    }
}
