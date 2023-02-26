package postTest;


import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.HomePage;
import pages.MyProfilePage;
import pages.PostPage;

public class ChangeTitleNameTest extends BaseTest {
    final String Post_Title1 = "Chyrva_TC_ChangeTitle" + Util.getDateAndTimeFormatted();
    final String Post_Title2 = "Chyrva_TC_NewTitleName" + Util.getDateAndTimeFormatted();

    @Before
    public void prepData() {
        homePage.openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(Post_Title1)
                .enterTextInInputBody("Body text")
                .selectTextInDropDownOptions("Приватне повідомлення")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()


        ;


    }

    @Test

    public void TC_2changeTitleName() {
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .clickOnCreatedPost(Post_Title1)
                .openCreatePostPage()
                .clickOnEditButton()
                .enterTextInInputTitle(Post_Title2)
                .selectValueInDropDownOptions("Group Message")
                .clickOnSavePostButton()
                .checkTextInSuccessMessage("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkEditedPostOneAndDisplayed(Post_Title2)

        ;
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(Post_Title2)
                .deletePostsWithTitleTillPresent(Post_Title1)


        ;

    }
}


