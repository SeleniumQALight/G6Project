package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_Yakymiv ";
    final String POST_BODY = "Some interesting body ";

    @Test
    public void TC1_createNewPost() {
        homePage.
                openHomePage()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectDropDownOptions("Приватне повідомлення")
//                .selectValueDropDownOptions("One Person")
                .clickSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()



        ;
    }
}
