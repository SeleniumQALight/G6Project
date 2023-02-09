package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {


    final String post_Title="TC1_sapaiev_1"+ Util.getDateAndTimeFormatted();


    @Test
    public void TS1_createNewPost(){
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(post_Title)
                .enterTextInIputBody("Test text")
                //.selectTextInDropdownOptions("Приватне повідомлення")
                .selectValueInDropdownOptions("One Person")
                .clickButtonCreatePost()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(post_Title);
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(post_Title)
        ;
    }

}
