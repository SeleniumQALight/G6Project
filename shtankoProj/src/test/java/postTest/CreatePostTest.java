package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;


public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_shtanko_" + Util.getDateAndTimeFormatted();
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
                .clickOnCheckbox("checked")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInNewTitle(POST_TITLE)
                .checkNotePost()
                .checkStatusPost("One Person")
                .verifyCheckboxState()
                .getHeaderElement().clickOnMyProfileButton()
                .checkUserName("qaauto")
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)

        ;

    }
    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE)
        ;

    }
}
