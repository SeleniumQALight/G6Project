package postTest;

import BaseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_smiian_1" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "text-text-text";
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
             .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInBodyContent(POST_BODY)
//                .selectTextInDropDownOption("Приватне повідомлення")
                .selectValueInDropDownOption("One Person")
                .clickOnSavePostButton()
             .checkInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE)
        ;

    }
}