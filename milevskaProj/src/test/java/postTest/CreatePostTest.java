package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;
import pages.CreatePostPage;
import pages.elements.HeaderElement;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_milevska_1"+ Util.getDateAndTimeFormatted();
    final String dropdownValue = "One Person";
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .headerElement.clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("POST_BODY")
                //.selectTextInDropDownOptions("Приватне повідомлення")
                .selectValueInDropDownOptions(dropdownValue)
                //.selectElementInDropdownByUI()
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTitleOfCreatedPost(POST_TITLE)
                .checkLabelNote()
                .checkCorrectSelectedValueInDropdown(dropdownValue)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsCorrectUserLogin("qaauto")
                .checkPostWasCreated(POST_TITLE)
        ;
    }
}
