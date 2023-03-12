package postTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostTestWithExcel extends BaseTest {
    String postTitle =  "";
    String postBody = "";
    String postIsUniqueState = "";
    String postDropDownOptions = "";
    String successMessage = "";
    String postExpectedLabel = "";

    public CreatePostTestWithExcel() throws IOException {
    }

    @Test
    public void TC_createNewPostWithExcel() throws IOException {
        Map<String, String> dataForPost = ExcelDriver.getData(configProperties.DATA_FILE(), "CreatePost");
        postTitle = dataForPost.get("Title") + Util.getDateAndTimeFormatted();
        postBody = dataForPost.get("Body");
        postIsUniqueState = dataForPost.get("StateOfPostUnique");
        postDropDownOptions = dataForPost.get("DropDownOptions");
        successMessage = dataForPost.get("SuccessMessage");
        postExpectedLabel = dataForPost.get("ExpectedLabel");
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(postTitle)
                .enterTextInInputBody(postBody)
                .setupStateOfPostUnique(postIsUniqueState)
                .selectValueInDropDownOptions(postDropDownOptions)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage(successMessage)
                .checkPostTitle(postTitle)
                .checkPostLabel(postExpectedLabel, postDropDownOptions)
                .checkIsPostUnique()
                .checkPostOptionValue(postDropDownOptions)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(postTitle)
        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(postTitle)
                ;
    }
}
