package postCreate;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    public static final String POST_TITLE = "test title TC1_Kovalnohov";

    @Test
    public void TC1_createNewPostTest(){
homePage.openHomePage()
        .getHeaderElements()
        .clickOnCreatePostButton()
        .checkIsRedirectToCreatePostPage()
        .typePostTitle(POST_TITLE)
        .typePostBody("Test post body")
      //  .selectItemInDropDown("Приватне повідомлення")
        //.selectValueInDropDown("One Person")
        .selectItemInDropDownByUI("Приватне повідомлення")
        .clickSavePostButton()
        .checkIsRedirectToPostPage()
        .checkValueInSuccessMessage("New post successfully created.")
        .checkIsPostTitlePresent()
        .checkIsNoteContainsUnderlineText()
        .checkIsCreatedPostTitleTextEqualWithExpected(POST_TITLE)
        .checkIsNoteTextEqualWithExpected("Note: This post was written for One Person")
        .getHeaderElements()
        .clickOnProfileButton()
        .checkIsRedirectProfilePage()
        .checkIsUserNameVisibleOnProfilePage(TestData.VALID_LOGIN);
    }


}
