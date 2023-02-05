package postCreate;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    public static final String POST_TITLE = "test title";

    @Test
    public void TC1_createNewPostTest(){
homePage.openHomePage()
        .clickOnCreatePostButton()
        .checkIsRedirectToCreatePostPage()
        .typePostTitle(POST_TITLE);

    }
}
