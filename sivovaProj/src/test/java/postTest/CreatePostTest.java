package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    final String POST_TITLE = "TC1_sivova";

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
                .checkRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
        ;

    }
}
