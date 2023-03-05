package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_berezhnyi_" + Util.getDateAndTimeFormatted();
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElements()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("This is the text entered into the body post")
         //.selectTextInDropDownOptions("Приватне повідомлення")
                .selectValueInDropDownByUI("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElements()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)

        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)

        ;
    }
}
