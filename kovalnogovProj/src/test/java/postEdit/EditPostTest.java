package postEdit;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    private String POST_TITLE = "Kovalnohov_" + Util.getDateAndTimeFormatted();
    private String POST_TITLE_UPDATED = "Kovalnohov_" + System.currentTimeMillis();

    @Before
    public void addNewPost() {
        homePage.openHomePage()
                .getHeaderElements()
                .clickOnCreatePostButton()
                .typePostTitle(POST_TITLE)
                .typePostBody("Test post body")
                .selectItemInDropDownByUI("Приватне повідомлення")
                .clickSavePostButton()
                .getHeaderElements()
                .clickOnProfileButton()
                .checkPostWasCreatred(POST_TITLE);
    }

    @Test
    public void testThatUserIsAbleToEditPost() {
        homePage.getHeaderElements()
                .clickOnProfileButton()
                .openPost(POST_TITLE)
                .editPost()
                .typePostTitle(POST_TITLE_UPDATED)
                .clickSaveUpdates()
                .checkValueInSuccessMessage("Post successfully updated.")
                .redirectToCurrentPostPage()
                .checkIsCreatedPostTitleTextEqualWithExpected(POST_TITLE_UPDATED)
                .getHeaderElements()
                .clickOnProfileButton()
                .checkIsRedirectProfilePage()
                .checkPostWasCreatred(POST_TITLE_UPDATED);

    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .getHeaderElements()
                .clickOnProfileButton()
                .checkIsRedirectProfilePage()
                .deletePostsWithTitleIfPresent(POST_TITLE_UPDATED);
    }
}
