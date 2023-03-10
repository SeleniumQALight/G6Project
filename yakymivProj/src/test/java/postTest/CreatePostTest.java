package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_Yakymiv" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Some interesting body ";
    final String POST_LABEL = "One Person";
    final String NOTE = "Note: This post was written for";
    final String NAME = "qaauto";
    final String CHECKED = "check";
    final String UNCHECKED = "uncheck";

    @Test
    public void TC1_createNewPost() {
        homePage.
                openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
//                .selectTextDropDownOptions("Приватне повідомлення")
//                .selectValueDropDownOptions(POST_LABEL)
                .selectTextInDropDownByUI("Приватне повідомлення")
                .setUpCheckBoxCreatePage(UNCHECKED)
                .clickSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique(UNCHECKED)
                .checkPostTitle(POST_TITLE)
                .checkPostNote(NOTE)
                .checkPostLabel(POST_LABEL)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsUserPresent(NAME)
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE)

                ;


    }
}
