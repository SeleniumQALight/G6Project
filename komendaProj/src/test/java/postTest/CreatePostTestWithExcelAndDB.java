package postTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CreatePostTestWithExcelAndDB extends BaseTest {

    String postTitle, postBody, selectTextInDropDown, successMessage, message1, message2, login;

    @Test
    public void TC_createPostWithExcel() throws IOException, SQLException, ClassNotFoundException {
        Map<String, String> dataNewPost = ExcelDriver.getData(CommonActionsWithElements.configProperties.DATA_FILE(), "CreatePost");
        login = dataNewPost.get("login");
        postTitle = dataNewPost.get("postTitle") + Util.getDateAndTimeFormatted();
        postBody = dataNewPost.get("postBody");
        selectTextInDropDown = dataNewPost.get("selectTextInDropDown");
        successMessage = dataNewPost.get("successMessage");
        message1 = dataNewPost.get("message1");
        message2 = dataNewPost.get("message2");


        homePage.openHomePageLoginDB(login)
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(postTitle)
                .enterTextInInputBody(postBody)
                .selectCheckBoxState("Check")
                .selectTextInDropDownByUIOptions(selectTextInDropDown)
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage(successMessage)
                .checkIsPostUnique()
                .checkIsTitleDisplayed(postTitle)
                .checkIsNoteDisplayed(message1)
                .checkIsUnderLineText(message2)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsCorrectLoginDisplayed(login)
                .checkPostWasCreated(postTitle)
        ;
    }

    @After
    public void deletePost() throws SQLException, ClassNotFoundException {
        homePage
                .openHomePageLoginDB(login)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(postTitle)
        ;
    }
}



