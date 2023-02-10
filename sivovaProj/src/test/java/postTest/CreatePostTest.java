package postTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    final String POST_TITLE = "TC1_sivova_" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "TC1_body";

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectPostTypeInDropdownByUI("Приватне повідомлення")
               //.selectPostTypeInDropdown("Приватне повідомлення")
               // .selectValueInDropdownOptions("One Person")
                .clickSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkCreatedPostTitle(POST_TITLE)
                .checkCreatedPostNote("Note: This post was written for One Person")
                .getHeaderElement().clickMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkUserNameDisplayed(TestData.VALID_LOGIN)
                .checkPostWasCreated(POST_TITLE)
        ;

    }

    @After
    public void deletePost (){
        homePage
                .openHomePage()
                .getHeaderElement().clickMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE)

        ;

    }

}


