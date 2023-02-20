package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EditPostTest extends BaseTest {
    final String POST_TITLE = "TC3_golenko_" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Test text in body";
    final String NEW_POST_TITLE = "TC3_golenko_updated_title";


    @Before
    public void CreatePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTitleIsDisplayed(POST_TITLE)
                .checkNoteIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;
    }


    @Test
    public void TC3_EditPostTest() {
        homePage
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
                .clickOnCreatedPost(POST_TITLE)
                .clickOnEditPostButton()
                .checkIsRedirectToEditPostPage()
                .enterNewPostTitle(NEW_POST_TITLE)
                .clickOnSaveUpdatesButton()
                .checkMessagePostUpdated()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostIsPresentAndUnique(NEW_POST_TITLE)
;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(NEW_POST_TITLE)
                .deletePostsWithTitleTillPresent(POST_TITLE)

        ;
    }
}
