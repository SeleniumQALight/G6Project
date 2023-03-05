package postTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String ONE_PERSON = "One Person";
    final String POST_TITLE = "TC1_golenko_" + Util.getDateAndTimeFormatted();


    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Test text in body")
                .selectTextInDropDownByUIOptions("Приватне повідомлення")
//                .selectTextInDropDownOptions("Приватне повідомлення")
//                .selectValueInDropDownOptions(ONE_PERSON)
                .setCheckboxSelected("check")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
             .checkTitleIsDisplayed(POST_TITLE)
                .checkNoteIsDisplayed()
                .checkSelectedValueIsDisplayed(ONE_PERSON)
            .checkTextInSuccessMessage("New post successfully created.")
                .checkPostIsUnique()
                .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()
                .checkIsCorrectLoginDisplayed(TestData.VALID_LOGIN)
                .checkIsRedirectToMyProfilePage()
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

