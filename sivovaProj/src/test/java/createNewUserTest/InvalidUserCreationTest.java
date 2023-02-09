package createNewUserTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class InvalidUserCreationTest extends BaseTest {


    @Test
    public void invalidUserCreation(){
    loginPage.openLoginPage();
    loginPage.enterUserNameIntoInputSignUp(TestData.INVALID_USERNAME);
    loginPage.checkErrorMessage(TestData.ERROR_MESSAGE_USERNAME);

    }

}
