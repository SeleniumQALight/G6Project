package postCreate;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    public static final String POST_TITLE = "test title TC1_Kovalnohov";

    @Test
    public void TC1_createNewPostTest(){
homePage.openHomePage()
        .clickOnCreatePostButton()
        .checkIsRedirectToCreatePostPage()
        .typePostTitle(POST_TITLE)
        .typePostBody("Test post body")
        .selectItemInDropDown("Приватне повідомлення")
        //.selectValueInDropDown("One Person")
        .clickSavePostButton()
        .checkIsRedirectToPostPage()
        .checkValueInSuccessMessage("New post successfully created.")
        .getHeaderElements()
        .clickOnProfileButton()
        .checkIsRedirectProfilePage();


    }
}
