package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangePostNameTest extends BaseTest {
    final String POST_TITLE = "TC1_Sydorenko_" + Util.getDateAndTimeFormatted();
    final String NEW_POST_TITLE = "NEW TC4_Sydorenko_" + Util.getDateAndTimeFormatted();

    @Test
    public void changePostNameTest() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .clickOnPostWithTitle(POST_TITLE)
                .clickOnEditButton()
                .enterTextInInputTitle(NEW_POST_TITLE)
                .clickOnSavePostButton()
                .getHeaderElement().clickOnMyProfileButton()
                .checkPostWasCreated(NEW_POST_TITLE)
        ;
    }

    @Before
    public void createPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Test")
                .selectValueInDropDownOptionsByUI("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIfCreatedPostHasCorrectTitle(POST_TITLE)
                .checkIfCreatedPostPageHasCorrectNote("Note: This post was written for")
                .checkIfCreatedPostPageUnderLinedTextIs("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIfMyProfilePageHasCorrectUser("qaauto")
                .checkPostWasCreated(POST_TITLE);
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(NEW_POST_TITLE)
        ;
    }
}
