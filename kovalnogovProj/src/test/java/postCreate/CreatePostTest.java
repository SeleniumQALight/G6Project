package postCreate;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.After;
import org.junit.Test;
import pages.CommonActionsWithElement;

public class CreatePostTest extends BaseTest {

    public static final String POST_TITLE = "TC1_Kovalnohov"+System.currentTimeMillis();

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
        .selectCheckBox(CommonActionsWithElement.CheckBoxState.CHECKED)
        .selectItemInDropDownByUI("Приватне повідомлення")
        .clickSavePostButton()
        .checkIsRedirectToPostPage()
        .checkValueInSuccessMessage("New post successfully created.")
        .checkIsPostTitlePresent()
        .checkIsNoteContainsUnderlineText()
        .checkIsCreatedPostTitleTextEqualWithExpected(POST_TITLE)
        .checkThatPostUnique()
        .checkIsNoteTextEqualWithExpected("Note: This post was written for One Person")
        .getHeaderElements()
        .clickOnProfileButton()
        .checkIsRedirectProfilePage()
        .checkPostWasCreatred(POST_TITLE)
        .checkIsUserNameVisibleOnProfilePage(TestData.VALID_LOGIN);
    }

@After
public void deletePost(){
        homePage.openHomePage()
                .getHeaderElements()
                .clickOnProfileButton()
                .checkIsRedirectProfilePage()
                .deletePostsWithTitleIfPresent(POST_TITLE);
}
}
