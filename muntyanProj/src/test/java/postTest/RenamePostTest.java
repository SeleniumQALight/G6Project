package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RenamePostTest extends BaseTest {
    final String POST_TITLE = "TC1_muntyan_" + Util.getDateAndTimeFormatted();
    final String POST_TITLE_NEW = "TC1_muntyan_" + Util.getDateAndTimeFormatted();

    @Before
    public void BeforeCreateNewPost() { //create TC
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInputTitle(POST_TITLE)
                .enterTextInputBody("Text Body")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkisTitlePostDisplayed()
                .getHeaderElement().clickOnMyProfileButton()
                .checkPostWasCreated(POST_TITLE);

    }


    @Test

    public void TC41RenamePost() {
        homePage
                .openHomePage()
                .clickOnCreatedPost(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .checkIsRedirectedToEditPostPage()
                .enterTitle(POST_TITLE_NEW)
                .clickOnSaveUpdatesButton()
                .checkIsRedirectedToEditPostPage()
                .checkIsConformationMessageDisplayed()
        ;
    }

//    @After
//    public void deletePost(){
//        homePage.openHomePage()
//                .getHeaderElement().clickOnMyProfileButton()
//                .checkIsRedirectToMyProfilePage()
//                .deletePostsWithTitleTillPresent(POST_TITLE_NEW)
//
//        ;
//
//    }
}

