package createNewUserTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class InvalidUserCreationTest extends BaseTest {


    @Test
    public void invalidUserCreation(){
    loginPage
            .openLoginPage()
            .enterUserNameIntoInputSignUp(TestData.INVALID_USERNAME)
            .enterEmailIntoInputSignUp(TestData.INVALID_EMAIL)
            .enterPasswordInputSignUp(TestData.INVALID_PASSWORD)
            .checkErrorMessage(TestData.ERROR_MESSAGE_USERNAME)
            .checkErrorMessage(TestData.ERROR_MESSAGE_EMAIL)
            .checkErrorMessage(TestData.ERROR_MESSAGE_PASSWORD)

    ;

    }

}
