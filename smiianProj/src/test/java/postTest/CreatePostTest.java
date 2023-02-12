package postTest;

import BaseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_smiian";
    final String POST_BODY = "text-text-text";
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
             .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInBodyContent(POST_BODY)
//                .selectTextInDropDownOption("Приватне повідомлення")
//                .selectValueInDropDownOption("One Person")
                .selectSecondTextInDropDownByUi()
                .clickOnSavePostButton()
             .checkInSuccessMessage("New post successfully created.")
             .checkTitleIsVisible()
             .checkNoteIsVisibleByText(POST_BODY)
             .checkNoteIsVisible()
             .checkNoteSecondOptionIsVisible()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
        ;
    }
}