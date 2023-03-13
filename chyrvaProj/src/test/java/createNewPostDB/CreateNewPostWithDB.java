package createNewPostDB;

import baseTest.BaseTest;
import dbTest.DBTest;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class CreateNewPostWithDB  extends BaseTest{

    private Database mysqlDB;
    private Logger log = Logger.getLogger(DBTest.class);

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();

    }

    @After
    public void tearDown() throws SQLException {
        mysqlDB.quit();

    }
@Test
    public void createNewPostWithDB() throws SQLException, ClassNotFoundException {

    homePage.openHomePageDB()
            .getHeaderElement().clickOnCreatePostButton()
            .checkIsRedirectToCreatePostPage();
//            .enterTextInInputTitle(POST_TITLE)
//            .enterTextInInputBody("Body text")
//            .selectDesiredCheckBoxStatus("check")
//            .selectTextInDropDownOptions("Приватне повідомлення")
//            .selectValueInDropDownOptions("One Person")
//            .clickOnSavePostButton()
//            .checkIsRedirectToPostPage()
//            .checkTextInSuccessMessage("New post successfully created.")
//            .checkNewTitleIsDisplayed(POST_TITLE)
//            .checkIsNoteDisplayedOnPostPage("Note: This post was written for One Person")
//            .checkTextisDisplayedInBody("One Person")
//            .getHeaderElement().clickOnMyProfileButton()
//            .checkIsRedirectToMyProfilePage()
//            .checkPostWasCreated(POST_TITLE)
//
//            .checkUserIsDisplayedOnMyProfilePage(UserName)


}


}
