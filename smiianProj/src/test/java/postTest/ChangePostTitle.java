package postTest;

import BaseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class ChangePostTitle extends BaseTest {
    final String POST_TITLE = "TC3_smiian_";
    final String POST_BODY = "TC3-text-text-text";
    final String POST_TITLE_EDITED = POST_TITLE + "ABCD";

    @Test
    public void TC3_changePostTitle(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
             .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInBodyContent(POST_BODY)
                .selectSecondTextInDropDownByUi()
                .clickOnSavePostButton()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
             .checkPostWasCreated(POST_TITLE)
                .clickOnThePostByTitle(POST_TITLE)
                .clickOnEditButton()
             .checkOnThePostEditPage()
                .enterTextInInputTitle(POST_TITLE_EDITED)
                .clickOnSaveUpdate()
             .checkSuccessMessageIsVisible()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
             .checkPostWasCreated(POST_TITLE_EDITED)
                ;
    }
    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE_EDITED)
        ;
    }
}
