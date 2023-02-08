package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = " TC1_zapotochna";

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(" Text testing")
               .selectTextInDropDownOptions("Приватне повідомлення")
              //  .selectValueInDropDownOptions("One Person")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()


;

    }


}
