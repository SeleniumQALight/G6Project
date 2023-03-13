package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_kucheriuk_" + Util.getDateAndTimeFormatted();

    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Test")
             //   .selectTextInDropDownOptions("Приватне повідомлення")
             //   .selectValueInDropDownOptions("One Person")
                .selectCheckboxStateSelected("Check")
                .selectValueInDropDownOptionsUI("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkPostUnique()
                .checkTitleInCreatedPost(POST_TITLE)
                .checkNoteInCreatedPost("Note: This post was written for")
                .checkTextUnderLine("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkUser("qaauto")
                .checkPostWasCreated(POST_TITLE)
        ;

    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)
        ;
    }
}
