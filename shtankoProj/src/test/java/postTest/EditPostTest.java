package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String POST_TITLE = "tc2_shtanko_" + Util.getDateAndTimeFormatted();
    final String POST_TITLE_EDIT = "TC2_SHTANKO_" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "TC2_Shtanko post body";
    @Before
    public void tc2_BeforeCreatePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectTextInDropDownOptions("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInNewTitle(POST_TITLE)
                .checkNotePost()
                .checkStatusPost("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkUserName("qaauto")
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
        ;
    }
    @Test
    public void tc2_TestEditPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .clickOnTitlePost(POST_TITLE)
                .checkTextInNewTitle(POST_TITLE)
                .clickOnEditButton()
                .enterEditTextInInputTitle(POST_TITLE_EDIT)
                .clickOnSaveUpdateButton()
                .checkTextInSuccessUpdatedMessage("Post successfully updated.")
                .clickOnBackPostButton()
                .checkTextInNewTitle(POST_TITLE_EDIT)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE_EDIT)
        ;
    }
    @After
    public void tc2_DeletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE_EDIT)
                .deletePostWithTitleTillPresent(POST_TITLE);

    }

}
