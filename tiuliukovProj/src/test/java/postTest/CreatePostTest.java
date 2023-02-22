package postTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_Tiuliukov_" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Post body";
    final String OPTION_TEXT_ALL_USERS = "Загальнодоступне";
    final String OPTION_TEXT_ONE_PERSON = "Приватне повідомлення";
    final String OPTION_TEXT_GROUP_MESSAGE = "Групове повідомлення";
    final String OPTION_VALUE_ALL_USERS = "All Users";
    final String OPTION_VALUE_ONE_PERSON = "One Person";
    final String OPTION_VALUE_GROUP_MESSAGE = "Group Message";
    final String EXPECTED_LABEL = "Note: This post was written for ";
    final String EXPECTED_SUCCESS_MESSAGE = "New post successfully created.";

    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                //.selectTextInDropDownOption("Приватне повідомлення")
                .selectTextInDropDownOptionByUI(OPTION_TEXT_ONE_PERSON)
                //.selectValueInDropDownOptions("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage(EXPECTED_SUCCESS_MESSAGE)
                .checkPostTitle(POST_TITLE)
                .checkPostLabel(EXPECTED_LABEL, OPTION_VALUE_ONE_PERSON)
                .checkPostOptionValue(OPTION_VALUE_ONE_PERSON)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
                .checkIsDefaultNameDisplayed(TestData.VALID_LOGIN)
        //.getHeaderElement().checkIsDefaultLoginNameDisplayed(TestData.VALID_LOGIN)
        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)
                ;
    }
}
