package DataBaseTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

public class CreatePostWithDBExcelTest extends BaseTest {

    String POST_TITLE, POST_BODY, POST_LABEL, STATE_OF_CHECKBOX;

    @Test
    public void TC_createPostWithExcel() throws IOException {
        Map<String, String> dataNewPost = ExcelDriver.getData(CommonActionsWithElements.configProperties.DATA_FILE(), "CreatePostData");
        POST_TITLE = dataNewPost.get("postTitle") + Util.getDateAndTimeFormatted();
        POST_BODY = dataNewPost.get("postBody");
        POST_LABEL = dataNewPost.get("visibility");
        STATE_OF_CHECKBOX = dataNewPost.get("checkbox");


        homePage.
                openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectValueDropDownOptions(POST_LABEL)
                .setUpCheckBoxCreatePage(STATE_OF_CHECKBOX)
                .clickSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique(STATE_OF_CHECKBOX)
                .checkPostTitle(POST_TITLE)
                .checkPostLabel(POST_LABEL)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE)

        ;


    }
}


