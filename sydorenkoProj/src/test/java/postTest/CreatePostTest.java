package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_sydorenko_" + Util.getDateAndTimeFormatted();

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Test")
//                .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions("One Person")
                .selectValueInDropDownOptionsByUI("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIfCreatedPostHasCorrectTitle(POST_TITLE)
                .checkIfCreatedPostPageHasCorrectNote("Note: This post was written for")
                .checkIfCreatedPostPageUnderLinedTextIs("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIfMyProfilePageHasCorrectUser("qaauto");
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @After
    public void deletePost(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)
        ;
    }

}
