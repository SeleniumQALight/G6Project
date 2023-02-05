package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_muntyan";

    @Test
    public void TC1_createNewPost() { //number of the test can be taken from TestRail and should match
    homePage
            .openHomePage()
            .clickOnCreatePostButton()
            .checkIsRedirectToCreatePostPage()
            .enterTextInputTitle(POST_TITLE)
            .enterTextInputBody("Text Body")
            .selectTextInDropDownOptions("Приватне повідомлення")
         //   .selectValueInDropDownOptions("One Person")
            .clickOnSavePostButton()
            .checkIsRedirectToPostPage()
            .checkTextInSuccessMessage("New post successfully created.")
            .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()

    ;

    }
}
