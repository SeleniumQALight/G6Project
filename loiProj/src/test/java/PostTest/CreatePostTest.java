package PostTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_Loi_" + Util.getDateAndTimeFormatted();
    final String POST_BODY_CONTENT = "text";

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInTextareaBodyContent(POST_BODY_CONTENT)
                .selectTextInDropDownOptions("Приватне повідомлення")
//              .selectValueInDropDownOptions("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsTitleMatches(POST_TITLE)
                .checkIsNotePresent()
                .checkIsAccessRightMatches("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsUserNameMatches(TestData.VALID_LOGIN)
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
