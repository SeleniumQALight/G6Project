package postTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostWithExcelAndDB extends BaseTest {
    final String ONE_PERSON = "One Person";
    Map<String, String> dataForNewPost =
            ExcelDriver.getData(configProperties.DATA_FILE(), "createPost");
    final String MY_POST_TITLE = dataForNewPost.get("postTitle") + Util.getDateAndTimeFormatted();

    public CreatePostWithExcelAndDB() throws IOException {
    }


    @Test
    public void TC_createNewPostWithExcel() {

        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(MY_POST_TITLE)
                .enterTextInInputBody(dataForNewPost.get("bodyText"))
                .selectTextInDropDownByUIOptions(dataForNewPost.get("dropDownValue"))
//                .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions(ONE_PERSON)
                .setCheckboxSelected(dataForNewPost.get("checkboxState"))
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTitleIsDisplayed(MY_POST_TITLE)
                .checkNoteIsDisplayed()
                .checkSelectedValueIsDisplayed(ONE_PERSON)
                .checkTextInSuccessMessage("New post successfully created.")
                .checkPostIsUnique()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsCorrectLoginDisplayed(TestData.VALID_LOGIN)
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(MY_POST_TITLE)
        ;
    }


    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(MY_POST_TITLE)

        ;

    }

}
