package PostTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_loi";
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
        ;
    }
}
