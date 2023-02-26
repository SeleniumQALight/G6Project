
package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class MessagesInRegistrationForm extends BaseTest {

    final String USER_NAME = "tr";
    final String EMAIL = "test.com";
    final String PASSWORD = "123";


    @Test
    public void TC4_checkMessagesSignUpForm (){
        loginPage
                .openLoginPage()
                .enterUserNameIntoInpuPick_a_username(USER_NAME)
                .enterEmailIntoInputEmail(EMAIL)
                .enterPasswordIntoInputCreate_a_password(PASSWORD)
                //  .clickOnSign_up_for_OurApp()
        ;
        ;
        loginPage.checknumberOfMessages(3)
                .checkAlertMessagesContent(TestData.MESSAGE_1)
                .checkAlertMessagesContent(TestData.MESSAGE_2)
                .checkAlertMessagesContent(TestData.MESSAGE_3)
        ;
    }
}

