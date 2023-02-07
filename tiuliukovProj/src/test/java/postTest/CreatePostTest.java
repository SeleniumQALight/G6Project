package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_Tiuliukov_" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "New post successfully created.";

    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                //.selectTextInDropDownOption("Приватне повідомлення")
                .selectValueInDropDownOptions("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage().
                checkPostWasCreated(POST_TITLE)
        ;
    }
}
