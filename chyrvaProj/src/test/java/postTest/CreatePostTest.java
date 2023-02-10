package postTest;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_chyrva";
final String UserName = "qaauto";

    @Test
    public void TC1_createNewPost(){
        homePage.openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Body text")
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
                .checkUserIsDisplayedOnMyProfilePage(UserName)
                .selectTextInDropDownByUI()








                ;


    }
}
