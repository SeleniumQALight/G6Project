package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangeTitlePost extends BaseTest {
    final String POST_TITLE = "TC1_kucheriuk_" + Util.getDateAndTimeFormatted();
    final String BODY = "Test";
    final String CHANGE_TITLE = "NEW_TC1_kucheriuk_" + Util.getDateAndTimeFormatted();

    @Before
    public void createPost(){
        homePage.openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(BODY)
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE);
    }

    @Test
    public void changeTitlePost(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .clickOnCreatedPost(POST_TITLE)
                .clickOnEditButton()
                .addChangedTitle(CHANGE_TITLE)
                .clickOnSavePostButton()
                .checkTextInSuccessUpdateMessage("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkPostWasCreated(CHANGE_TITLE);
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(CHANGE_TITLE)
                .deletePostsWithTitleTillPresent(POST_TITLE);
    }
}
