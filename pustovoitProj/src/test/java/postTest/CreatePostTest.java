package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_pustovoit_" + Util.getDateAndTimeFormatted();

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Test Text body")
                .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions("One Person")
//                .selectTextInDropDownByUI("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTitleCreatedPost(POST_TITLE )
                .checkNoteIsDisplayedAfterCreatedPost("This post was written for")
                .checkTextUnderLineChooseDropDown("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkUserNameOnProfilePage("qaauto")
                .checkPostWasCreated(POST_TITLE)
                ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)
        ;
    }
}
