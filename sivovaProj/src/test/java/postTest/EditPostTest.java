package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {


    final String POST_BODY = "TC2_body";

    private String postTitle = "TC2_sivova_" + Util.getDateAndTimeFormatted();
    private String newTitle = "new title sivova";


    @Before
    public void createPost() {
        homePage
                .openHomePage()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .enterTextInInputTitle(postTitle)
                .enterTextInInputBody(POST_BODY)
                .selectValueInDropdownOptions("One Person")
                .clickSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")

        ;

    }

    @Test
    public void editPost() {
                homePage
                        .openHomePage()
                        .getHeaderElement().clickMyProfileButton("qaauto")
                        .checkIsRedirectToMyProfilePage()
                        .openPostWithTitle(postTitle)
                        .checkIsRedirectToPostPage()
                        .clickOnEditButton()
                        .modifyPostTitle (newTitle)
                        .checkTextInSuccessMessage("Post successfully updated.")
                        .getHeaderElement().clickMyProfileButton("qaauto")
                        .checkIsRedirectToMyProfilePage()
                        .checkPostWasCreated(newTitle)

                        ;
    }

    @After

    public void deletePost() {

        homePage
                .openHomePage()
                .getHeaderElement().clickMyProfileButton("qaauto")
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(newTitle)
                .deletePostWithTitleTillPresent(postTitle)

        ;

    }

}
