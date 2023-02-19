package postTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest  extends BaseTest {
    final String POST_TITLE = "TC1_komenda_" + Util.getDateAndTimeFormatted();
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
            .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Body text")
//               .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions("One Person")
                .selectTextInDropDownByUIOptions("Приватне повідомлення")
                .clickOnSavePostButton()
            .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsTitleDisplayed(POST_TITLE) //HW#3 - Перевірити що ви бачите створений Title на сторінці PostPage
                .checkIsNoteDisplayed("Note: This post was written for") //HW#3 - Перевірити що присутня лейба Note: This post was written for
                .checkIsUnderLineText("One Person") //HW#3 - Перевірити що присутня що в body присутній текст з "підкресленням" і той текст "One Person"
                .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()
                .checkIsCorrectLoginDisplayed(TestData.VALID_LOGIN) //HW#3 - Перевірити що присутня на сторінці MyProfile, перевірити що бачимо Юзера, яким залогінитися.
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)



        ;
    }

}
