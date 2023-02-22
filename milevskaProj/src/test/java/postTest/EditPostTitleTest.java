package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTitleTest extends BaseTest {
    final String POST_TITLE = "TC1_milevska_1" + Util.getDateAndTimeFormatted();
    final String dropdownValue = "One Person";
    final String EDITED_POST_TITLE = "Edited" + POST_TITLE;

    @Before
    public void createNewPost() {
        homePage
                .openHomePage()
                .headerElement.clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("POST_BODY")
                .selectTextInDropDownOptions("Приватне повідомлення")
                .selectValueInDropDownOptions(dropdownValue)
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTitleOfCreatedPost(POST_TITLE)
                .checkLabelNote("Note: This post was written for")
                .checkCorrectSelectedValueInDropdown(dropdownValue)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsCorrectUserLogin("qaauto")
                .checkPostWasCreated(POST_TITLE)
        ;
    }
    @Test
    public void changePostTitle(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .openEditPageForPostWithTitle(POST_TITLE)
                .checkIsRedirectToEditPostPage()
                .editPostWithTitle(EDITED_POST_TITLE);
    }

    @After
    public void checkIsPostEdited(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWasEdited(POST_TITLE , EDITED_POST_TITLE);
    }

    @After
    public void deleteCreatedTest(){
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deleteEditedPost(EDITED_POST_TITLE);
    }
}
