package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_Tiuliukov";
    final String POST_BODY = "New post successfully created.OP";
    final String OPTION_ALL_USERS = "Загальнодоступне";
    final String OPTION_ONE_PERSON = "Приватне повідомлення";
    final String OPTION_GROUP_MESSAGE = "Групове повідомлення";

    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                //.selectTextInDropDownOption("Приватне повідомлення")
                .selectTextInDropDownOptionByUI(OPTION_ONE_PERSON)
                //.selectValueInDropDownOptions("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
        ;
    }
}
