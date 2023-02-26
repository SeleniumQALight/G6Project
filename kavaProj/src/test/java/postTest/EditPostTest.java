package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    final String POST_TITLE = "Kava_Edit" + Util.getDateAndTimeFormatted();

    @Before
    public void createPost() {
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextIntoPostBody("This is edit post test")
//                .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions("One Person")
                .selectTextInDropDownUI("Загальнодоступне")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInTitle(POST_TITLE)
                .checkTextInLabel()
                .checkUsersText("All Users")
                .getHeaderElement().clickOnMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkUserVisibility("qaauto")
                .checkPostWasCreated(POST_TITLE);

    }

    @Test
    public void editPost() {
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfile()
                .checkIsRedirectToMyProfilePage()
                .clickOnPost(POST_TITLE)
                .checkIsRedirectToPostPage()
                .checkTextInTitle(POST_TITLE)
                .clickOnEditButton();

    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE);
    }

}
