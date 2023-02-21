package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Before;
import org.junit.Test;

public class EditPostTitleTest extends BaseTest {
    final String POST_TITLE = "TC1_milevska_1" + Util.getDateAndTimeFormatted();
    final String dropdownValue = "One Person";
    @Test
    @Before
    public void createPost(){
        CreatePostTest createPostTest = new CreatePostTest();
        createPostTest.TC1_createNewPost();
    }

    public void changePostTitle(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage();
                //.editPost(POST_TITLE);

    }
}
