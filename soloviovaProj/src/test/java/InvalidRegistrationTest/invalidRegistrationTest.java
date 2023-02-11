package InvalidRegistrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class invalidRegistrationTest extends BaseTest {

    @Test
    public void TC_2_invalidRegistrationTest(){
        loginPage.openLoginPage()
                   .enterUserNameToRegister("qw")
                   .enterEmailToRegister("test.com")
                   .enterPasswordToRegister("123")
                   .clickSignUpButton()
                   .checkIsAlertMessagesDisplayed()
                   .checkAlertMessagesContent()
        ;
    }

}
