package CreatPostWithExelAndDB;

import baseTest.BaseTest;
import libs.DB_Util;
import libs.ExcelDriver;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostWithExelAndDB extends BaseTest {


    final String post_Title = "TC1_sapaiev_1"+ Util.getDateAndTimeFormatted();




    @Test
    public void TS1_createNewPost() throws SQLException, ClassNotFoundException, IOException {
        Map<String,String> dataValidLogin=
                ExcelDriver.getData(configProperties.DATA_FILE(),"sheetHomeWork");


        homePage
                .openHomePageWithDataBase()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(dataValidLogin.get("title"))
                .enterTextInIputBody(dataValidLogin.get("body"))
                .operationWithCheckBox(dataValidLogin.get("Check-box"))
                //.selectTextInDropdownOptions("Приватне повідомлення")
                //.selectValueInDropdownOptions("One Person")
                .selectTextInDropDownByUIOptions(dataValidLogin.get("dropDown"))
                .clickButtonCreatePost()
                .checkIsRedirectToPostPage()
                .checkIsSelectingOfCheckoxInCreatingPost("yes")
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTitle(dataValidLogin.get("title")) //3 задание проверка тайтла
                .checkNoteDisplay()//3 задание проверка наличия лейба Note: This post was written for
                .checkUnderlineText("Group Message")//3 задание поиск подчеркивающей строки
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsRedirectToMyProfilePageByName("newqaauto")//4 задание проверка логина
                .checkPostWasCreated(dataValidLogin.get("title"));
    }

    @After
    public void deletePost() throws IOException {
        Map<String,String> dataValidLogin=
                ExcelDriver.getData(configProperties.DATA_FILE(),"sheetHomeWork");
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(dataValidLogin.get("title"))
        ;
    }


}
