package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    final String POST_TITLE = "TC1_berezhnyi_" + Util.getDateAndTimeFormatted();
    final String POST_TITLE_EDITED = "TC1_edited_" + Util.getDateAndTimeFormatted();

    @Before
    public void CreateNewPostForEdit(){
        homePage
                .openHomePage()
                .getHeaderElements().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("This is the text entered into the body post")
                .selectValueInDropDownByUI("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkPostTitleIsDisplayed(POST_TITLE)
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @Test
    public void EditCreatedPost(){
        homePage
                .openHomePage()
                .getHeaderElements().clickOnMyProfileButton()
                .clickOnCreatedPost(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .checkIsRedirectedToEditPage()
                .editPostTitle(POST_TITLE_EDITED)
                .clickTheSaveUpdatesButton()
                .checkTextInSuccessMessage("Post successfully updated.")
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE_EDITED)
        ;
    }

    @After
    public void DeleteCreatedPost(){
        homePage
                .openHomePage()
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)
                .deletePostsWithTitleTillPresent(POST_TITLE_EDITED)
        ;
    }

}
