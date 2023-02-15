package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String POST_TITLE = "TC2_Loi_" + Util.getDateAndTimeFormatted();
    final String EDIT_POST_TITLE = "TC2_Loi_Changed_" + Util.getDateAndTimeFormatted();
    final String POST_BODY_CONTENT = "text";

    @Before
    public void createPostForEdit() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInTextareaBodyContent(POST_BODY_CONTENT)
                .selectTextInDropDownOptions("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE);
    }

    @Test
    public void editPostTest() {
        myProfilePage
                .clickOnPostItem(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .enterTextInInputTitle(EDIT_POST_TITLE)
                .clickOnSaveUpdatesButton()
                .checkTextInSuccessMessage("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(EDIT_POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(EDIT_POST_TITLE)
        ;
    }
}
