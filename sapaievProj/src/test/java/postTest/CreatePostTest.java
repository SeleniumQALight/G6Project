package postTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class CreatePostTest extends BaseTest {


    final String post_Title = "TC1_sapaiev";


    @Test
    public void TS1_createNewPost() {
        homePage
                .openHomePage()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(post_Title)
                .enterTextInIputBody("Test text")
                //.selectTextInDropdownOptions("Приватне повідомлення")
                //.selectValueInDropdownOptions("One Person")
                .selectTextInDropDownByUIOptions("Групове повідомлення") //первое задание
                .clickButtonCreatePost()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTitle(post_Title) //3 задание проверка тайтла
                .checkNoteDisplay()//3 задание проверка наличия лейба Note: This post was written for
                .checkUnderlineText("Group Message")//3 задание поиск подчеркивающей строки
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsRedirectToMyProfilePageByName(TestData.VALID_LOGIN)//4 задание проверка логина

        ;


    }
}
