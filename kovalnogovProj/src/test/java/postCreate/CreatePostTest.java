package postCreate;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    public static final String POST_TITLE = "TC1_Kovalnohov"+System.currentTimeMillis();

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
        .checkIsRedirectProfilePage()
        .checkPostWasCreatred(POST_TITLE);
    }
}
