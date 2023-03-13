package postTest;

import baseTest.BaseTest;
import dbtest.DBTest;
import libs.Database;
import libs.ExcelDriver;
import libs.MySQL_Database;
import libs.Util;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostWithDBAndExcelTest extends BaseTest {

    String myUniqueTitle;

    private Database mysqlDB;
    private Logger log = Logger.getLogger(DBTest.class);

    @Before
    public void setUP() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();
    }


    @After
    public void tearDown() throws SQLException {

        mysqlDB.quit();
    }


    @Test
    public void testDataFromDb() throws SQLException, ClassNotFoundException, IOException {

        final String LOGIN = "newqaauto";


        String passwordFromDB =
                mysqlDB.selectValue(String.format("SELECT password FROM seleniumUsers WHERE login = '%s'", LOGIN));
        log.info(passwordFromDB);

        log.info("---------");


        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin(LOGIN);
        loginPage.enterPasswordIntoInputPassword(passwordFromDB);
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



