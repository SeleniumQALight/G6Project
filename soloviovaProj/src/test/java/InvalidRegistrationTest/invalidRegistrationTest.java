package InvalidRegistrationTest;

import baseTest.BaseTest;
import libraries.TestData;
import org.junit.Test;

public class invalidRegistrationTest extends BaseTest {

    @Test
    public void TC_2_invalidRegistrationTest(){
        loginPage.openLoginPage()
                   .enterUserNameToRegister("qw")
                   .enterEmailToRegister("test.com")
                   .enterPasswordToRegister("123")
                   .clickSignUpButton()
                   .checkIsAlertMessagesDisplayed(3)
                   .checkAlertMessagesContent(TestData.MESSAGE_1)
                   .checkAlertMessagesContent(TestData.MESSAGE_2)
                   .checkAlertMessagesContent(TestData.MESSAGE_3)
        ;
    }

}
