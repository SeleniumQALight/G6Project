import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_Kava";

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextIntoPostBody("This is text for test2 purposes")
//                .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions("One Person")
                .selectTextInDropDownUI("Загальнодоступне")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInTitle(POST_TITLE)
                .checkTextInLabel()
                .checkUsersText("All Users")
                .getHeaderElement().clickOnMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkUserVisibility("qaauto")

        ;
    }
}

