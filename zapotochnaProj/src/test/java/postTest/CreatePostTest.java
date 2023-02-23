package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_zapotochna_" + Util.getDateAndTimeFormatted();

    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)

                .enterTextInInputBody("Body. Text testing")
               // .selectTextInDropDownOptions("Приватне повідомлення")
               // .selectValueInDropDownOptions("One Person")

              .selectTextInDropDownByUI("Приватне повідомлення")

                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")

                .checkIfPostTitleCorrect(POST_TITLE)

                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()

                .checkIsProfileCorrect("qaauto")

                .checkPostWasCreated(POST_TITLE)


        ;

    }

    @After //якщо буде два after , то буде сортуваання по назві метода
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTittleTillPresent(POST_TITLE)
        ;
    }

}
