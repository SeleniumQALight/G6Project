package postTest;

import baseTest.BaseTest;
import dbtest.DBTest;
import libs.DBselenuimUsersFromTable;
import libs.Database;
import libs.ExcelDriver;
import libs.Util;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostWithDBAndExcelTest extends BaseTest {

    String myUniqueTitle;

    @Test
    public void testDataFromDb() throws SQLException, ClassNotFoundException, IOException {

        final String LOGIN = "newqaauto";
        DBselenuimUsersFromTable dBselenuimUsersFromTable = new DBselenuimUsersFromTable();


        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin(LOGIN);

        loginPage.enterPasswordIntoInputPassword(dBselenuimUsersFromTable.getPassFromDBForLogin(LOGIN));
        loginPage.clickOnButtonLogin();

        Map<String, String> dataForCreatePost = ExcelDriver.getData(configProperties.DATA_FILE(), "createPost");

        myUniqueTitle = dataForCreatePost.get("title") + Util.getDateAndTimeFormatted();

        homePage.getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()

                .enterTextInInputTitle(myUniqueTitle)
                .enterTextInInputBody(dataForCreatePost.get("bodyText"))
                .selectTextInDropDownByUI(dataForCreatePost.get("dropDownValue"))

                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")


                .checkIfPostTitleCorrect(myUniqueTitle)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()


                .checkPostWasCreated(myUniqueTitle)

        ;


    }


    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTittleTillPresent(myUniqueTitle)
        ;
    }
}



