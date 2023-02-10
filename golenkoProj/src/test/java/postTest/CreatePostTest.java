package postTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String ONE_PERSON = "One Person";
    final String POST_TITLE = "TC1_golenko";


    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
            .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Test text in body")
                .selectTextInDropDownByUIOptions("Приватне повідомлення")
//                .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions(ONE_PERSON)
                .clickOnSavePostButton()
            .checkIsRedirectToPostPage()
             .checkTitleIsDisplayed(POST_TITLE)
                .checkNoteIsDisplayed()
                .checkSelectedValueIsDisplayed(ONE_PERSON)
            .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()
                .checkIsCorrectLoginDisplayed(TestData.VALID_LOGIN)

        ;

    }
}
