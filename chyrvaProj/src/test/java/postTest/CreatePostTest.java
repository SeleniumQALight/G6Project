package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_chyrva_" + Util.getDateAndTimeFormatted();
final String UserName = "qaauto";


    @Test
    public void TC1_createNewPost(){
        homePage.openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Body text")
                .selectDesiredCheckBoxStatus("check")
                .selectTextInDropDownOptions("Приватне повідомлення")
                .selectValueInDropDownOptions("One Person")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkNewTitleIsDisplayed(POST_TITLE)
                .checkIsNoteDisplayedOnPostPage("Note: This post was written for One Person")
                .checkTextisDisplayedInBody("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)

                .checkUserIsDisplayedOnMyProfilePage(UserName)
//                .selectTextInDropDownByUI()









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
