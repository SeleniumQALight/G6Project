package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String POST_TITLE = "TC1_Yakymiv_" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Some interesting body ";
    final String POST_TITLE_EDITED = POST_TITLE + "_edited";


    @Before
    public void createPost(){
        homePage.openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectTextDropDownOptions("Приватне повідомлення")
                .clickSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE);
    }

    @Test
    public void editPostTitleTest(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .clickOnPostWithTitle(POST_TITLE)
                .clickOnEditButton()
                .checkIsRedirectToEditPage()
                .editTitleOfCreatedPost(POST_TITLE_EDITED)
                .clickSaveUpdatesButton()
                .checkTextInSuccessMessage("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE_EDITED)
                ;
    }

    @After
    public void deletePost(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .deletePostWithTitleTillPresent(POST_TITLE)//in case we didn't change title
                .deletePostWithTitleTillPresent(POST_TITLE_EDITED)
                ;
    }
}
