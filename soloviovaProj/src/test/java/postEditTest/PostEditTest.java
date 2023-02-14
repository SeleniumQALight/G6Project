package postEditTest;

import baseTest.BaseTest;
import libraries.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostEditTest extends BaseTest {
    final String POST_TITLE = "Post Edit functionality test " + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam mattis faucibus malesuada. Pellentesque pretium eget felis sit amet pretium. Maecenas sollicitudin aliquet dignissim. Sed aliquam lorem non elementum tincidunt. Curabitur varius risus eu nibh porta pellentesque. Phasellus mollis sed dui sed facilisis.";
    final String NEW_POST_TITLE = "New Post Title made by Julia on "  + Util.getDateAndTimeFormatted();
    final String SUCCESS_MESSAGE = "New post successfully created.";
    final String UPDATE_SUCCESS_MESSAGE = "Post successfully updated.";


    @Before
    public void postCreationPrecondition(){
    homePage
                .openHomePage()
                .getHeaderElements().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage(SUCCESS_MESSAGE)
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
    ;
}

@Test
    public void PostEditingTest(){
    homePage
                .openHomePage()
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .findAndOpenPostToEdit(POST_TITLE)
                .checkIsRedirectToEditPage()
                .postTitleEdit(NEW_POST_TITLE)
                .saveUpdatedTitle()
                .checkSaccessUpdateMessage(UPDATE_SUCCESS_MESSAGE)
                .getHeaderElements().clickOnMyProfileButton()
                .checkPostWasCreated(NEW_POST_TITLE)
    ;
}

@After
public void deletePost(){
        homePage
                    .openHomePage()
                    .getHeaderElements().clickOnMyProfileButton()
                    .checkIsRedirectToMyProfilePage()
                    .deletePostsWithTitleTillPresent(NEW_POST_TITLE)
        ;
}
}
