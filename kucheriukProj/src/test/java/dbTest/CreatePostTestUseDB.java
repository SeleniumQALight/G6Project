package dbTest;

import baseTest.BaseTest;
import libs.DB_Util_SeleniumUsers;
import libs.Database;
import libs.ExcelDriver;
import libs.Util;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

import static pages.CommonActionsWithElements.configProperties;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CreatePostTestUseDB extends BaseTest {
    private Database mysqlDB;
    private Logger logger = Logger.getLogger(Database.class);

    final String LOGIN = "newqaauto";

    Map<String, String> dataForNewPost = ExcelDriver.getData(configProperties.DATA_FILE(), "createPostDB");

    final String POST_TITLE = dataForNewPost.get("postTitle") + Util.getDateAndTimeFormatted();

    final String POST_BODY = dataForNewPost.get("postBody");

    public CreatePostTestUseDB() throws IOException, SQLException {
    }

    @Test
    public void TC1_createPostTest() throws SQLException, ClassNotFoundException{

        DB_Util_SeleniumUsers db_util_seleniumUsers = new DB_Util_SeleniumUsers();

        logger.info(db_util_seleniumUsers.getPassForLogin(LOGIN));

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(LOGIN);
        loginPage.enterPasswordIntoInputPassword(db_util_seleniumUsers.getPassForLogin(LOGIN));
        loginPage.clickOnButtonLogin();
        homePage
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectCheckboxStateSelected(dataForNewPost.get("checkbox "))
                .selectValueInDropDownOptionsUI(dataForNewPost.get("dropdown"))
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTitleInCreatedPost(POST_TITLE)
                .checkNoteInCreatedPost("Note: This post was written for")
                .checkTextUnderLine("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkUser(LOGIN)
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
