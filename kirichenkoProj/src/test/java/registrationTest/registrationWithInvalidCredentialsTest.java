package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class registrationWithInvalidCredentialsTest extends BaseTest {

    @Test
    public void TC2_CheckRegistrationValidationMessages (){
        loginPage
                .fillingRegistrationFormFields()
                .checkValidationMassagesDisplayed()
                . checkErrorMessageText("Username must be at least 3 characters.")
                .checkErrorMessageText("You must provide a valid email address.")
                .checkTextInLoginErrorMessage("Password must be at least 12 characters.");
    }

}
