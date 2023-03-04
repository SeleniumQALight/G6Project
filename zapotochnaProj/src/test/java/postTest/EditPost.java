package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPost extends BaseTest {

    final String POST_TITLE = "TC1_zapotochna_";
    final String POST_TITLE_EDITED = "TC_2 Edited ! Zapotochna";

    @Before
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Body. Text testing")
                .selectTextInDropDownByUI("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIfPostTitleCorrect(POST_TITLE)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsProfileCorrect("qaauto")
                .checkPostWasCreated(POST_TITLE)
        ;
    }


    @Test

    public void TC2_editMyPost() {

        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()

                .findMyPostToEdit(POST_TITLE)

                .clickOnEditPostButton()
             //   .checkIsRedirectToEditPostPage()
                .editTitle(POST_TITLE_EDITED)
                .clickOnSaveUpdateButton()
                .checkPostWasCreated(POST_TITLE_EDITED)

        ;
    }


}
