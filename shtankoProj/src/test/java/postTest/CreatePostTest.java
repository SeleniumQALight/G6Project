package postTest;

import baseTest.BaseTest;
import org.junit.Test;


public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_shtanko";
    final String POST_BODY = "TC1_shtanko Body";
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                //.selectTextInDropDownOptions("Приватне повідомлення")
                //.selectValueInDropDownOptions("One Person")
                .selectTextInUIDropDownOptions("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInNewTitle("TC1_shtanko")
                .checkNotePost()
                .checkStatusPost("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkUserName("qaauto")
                .checkIsRedirectToMyProfilePage()

        ;

    }
}
