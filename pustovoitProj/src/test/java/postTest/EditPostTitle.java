package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTitle extends BaseTest {
    final String POST_TITLE = "NewPost " + Util.getDateAndTimeFormatted();
    final String POST_BODY = "NewPostBody";
    final String NEW_POST_TITLE ="Edit New Post Title " + Util.getDateAndTimeFormatted();
    final String UPDATED_MESSAGE = "Post successfully updated.";
    final String CREATED_MESSAGE = "New post successfully created.";

    @Before
    public void createNewPostBefore(){
        homePage.openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage(CREATED_MESSAGE)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @Test
    public void editPostTitle (){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .clickOnPostTitle(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .checkIsRedirectToEditPage()
                .editPostTitle(NEW_POST_TITLE)
                .clickOnButtonSaveUpdates()
                .checkSuccessesUpdatedMessageDisplayed(UPDATED_MESSAGE)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithNewTitleWasCreated(NEW_POST_TITLE)
        ;
    }

    @After
    public void deletedUpdatePost(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)
                .deletePostsWithTitleTillPresent(NEW_POST_TITLE)
                ;
    }

}
