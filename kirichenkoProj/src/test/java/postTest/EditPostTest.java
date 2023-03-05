package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    final String POST_TITLE = "TC3_kirichenko " + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Test text ";
    final String UPDATED_POST_TITLE = "TC3_kirichenko Update" +Util.getDateAndTimeFormatted();
    @Before
    public void TC3_updatePostTitle (){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInToBody(POST_BODY)
                .clickOnSaveButton()
                .checkIsRedirectToPostPage()
                .checkPostTitle(POST_TITLE)
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }

    @Test
    public void updatePostTitle(){
        homePage
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkPostWasCreated(POST_TITLE)
                .clickOnCreatedPost(POST_TITLE)
                .clickOnEditPostButton()
                .checkIsRedirectToEditPostPage()
                .enterNewPostTitle(UPDATED_POST_TITLE)
                .clickOnSaveUpdatesButton()
                .checkIsRedirectToEditPostPage()
                .checkMessagePostUpdated()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithUpdatedTitleIsPresent(UPDATED_POST_TITLE);
    }

    @After
    public void deleteUpdatedPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(UPDATED_POST_TITLE)
        ;
    }

}
