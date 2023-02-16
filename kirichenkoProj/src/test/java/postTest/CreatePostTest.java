package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_kirichenko_" + Util.getDateAndTimeFormatted();
    final String OPTION_TEXT_ONE_PERSON = "Приватне повідомлення";
    final String OPTION_TEXT_GROUP_MESSAGE = "Групове повідомлення";
    final String OPTION_TEXT_AllUsers_MESSAGE = "Загальнодоступне";

    final String OPTION_VALUE_ONE_PERSON = "One Person";
    final String OPTION_VALUE_GROUP_MESSAGE = "Group Message";
    final String OPTION_Value_ALL_USERS = "All Users";

    final String EXPECTED_LABEL = "Note: This post was written for ";
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInToBody("test")
                //.selectTextInDropDownOptions("Приватне повідомлення")
                //.selectValueInDropDownOption("One Person")
                .selectTextInDropDownOptionByUI(OPTION_TEXT_GROUP_MESSAGE)
                .clickOnSaveButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkPostTitle(POST_TITLE)
                .checkPostLabel(EXPECTED_LABEL,OPTION_VALUE_GROUP_MESSAGE)
                .checkPostOptionValue(OPTION_VALUE_GROUP_MESSAGE)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)


        ;

    }
}
