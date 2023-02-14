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
                .selectValueInDropDownOptions("One Person")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
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
