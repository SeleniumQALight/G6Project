package postTest;

import BaseTest.BaseTest;
import org.junit.Test;

public class ChangePostTitle extends BaseTest {
    final String POST_TITLE = "TC3_smiian_";
    final String POST_BODY = "TC3-text-text-text";
    final String POST_TITLE_EDITED = POST_TITLE + "ABC";

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
                .clickOnThePostByTitle(POST_TITLE)
                .clickOnEditButton()
                .enterTextInInputTitle(POST_TITLE_EDITED)
                .clickOnSaveUpdate()

        ;
    }

}
