package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_Kava_EditPost" + Util.getDateAndTimeFormatted();

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextIntoPostBody("This is text for test2 purposes")
//                .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions("One Person")
                .selectTextInDropDownUI("Загальнодоступне")
                .checkBoxState("uncheck")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInTitle(POST_TITLE)
                .checkTextInLabel()
                .checkUsersText("All Users")
                .isCheckBoxMessageCorrect("Is this post unique? : no")
                .getHeaderElement().clickOnMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkUserVisibility("qaauto")
                .checkPostWasCreated(POST_TITLE)

        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)


        ;
    }
}

