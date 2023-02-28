package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    final String post_Title = "TC1_sapaiev_1"+ Util.getDateAndTimeFormatted();

    final String new_postTitle="TC2_sapaiev_2"+ Util.getDateAndTimeFormatted();


    @Before
    public void createPost(){
        homePage
                .openHomePage()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(post_Title)
                .enterTextInIputBody("Test text")
                .selectTextInDropDownByUIOptions("Групове повідомлення")
                .clickButtonCreatePost()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(post_Title);
    }



    @Test
    public void editPost(){
        myProfilePage




                .editPostsWithTitle(post_Title, new_postTitle)
                .checkEditPostTitleMessage()
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkSizeListOfEditedPost(new_postTitle);
    }




    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(new_postTitle)
                .deletePostsWithTitleTillPresent(post_Title)
        ;
    }
}
