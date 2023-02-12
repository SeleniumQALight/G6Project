package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String POST_TITLE = "TC2_Tiuliukov_" + Util.getDateAndTimeFormatted();
    final String POST_TITLE_EDITED = POST_TITLE + " edited";
    final String POST_BODY = "Post body";
    final String POST_BODY_EDITED = POST_BODY + " edited";
    final String OPTION_VALUE_ALL_USERS = "All Users";
    final String OPTION_VALUE_ONE_PERSON = "One Person";
    final String OPTION_VALUE_GROUP_MESSAGE = "Group Message";
    final String EXPECTED_SUCCESS_MESSAGE = "New post successfully created.";
    final String EXPECTED_SUCCESS_UPDATE_MESSAGE = "Post successfully updated.";
    final String EXPECTED_LABEL = "Note: This post was written for ";

    @Before
    public void createPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectValueInDropDownOptions(OPTION_VALUE_ONE_PERSON)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage(EXPECTED_SUCCESS_MESSAGE)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE);
    }

    @Test
    public void TC2_EditPost(){
        headerElement
                .clickOnMyProfileButton()
                .openPostByTitle(POST_TITLE)
                .clickOnEditButton()
                .changePostTitleTo(POST_TITLE_EDITED)
                .changePostBodyTo(POST_BODY_EDITED)
                .selectValueInDropDownOptions(OPTION_VALUE_GROUP_MESSAGE)
                .clickOnSaveUpdatesButton()
                .checkIsSuccessUpdateMessagePresent(EXPECTED_SUCCESS_UPDATE_MESSAGE)
                .clickOnBackToPostPermalink()
                .checkPostTitle(POST_TITLE_EDITED)
                .checkPostLabel(EXPECTED_LABEL, OPTION_VALUE_GROUP_MESSAGE)
                .checkBodyContent(POST_BODY_EDITED)
                ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE_EDITED)
        ;
    }

}
