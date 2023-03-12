package dbTest;

import baseTest.BaseTest;
import libs.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElement.configProperties;

public class DBCreatePostTest extends BaseTest {
    private Database mysqlDB;
    private Logger logger = Logger.getLogger(DBTest.class);
    private String CHANGE_TITLE;
    @Test
    public void testDataFromDB() throws SQLException, IOException, ClassNotFoundException {
        Map<String, String> createPost= ExcelDriver.getData(configProperties.DATA_FILE(), "createPost");
        CHANGE_TITLE = createPost.get("title") + Util.getDateAndTimeFormatted();

        final String LOGIN = "newqaauto";
        DB_Util_SeleniumUsers db_util_seleniumUsers = new DB_Util_SeleniumUsers();
        logger.info(db_util_seleniumUsers.getPassForLogin(LOGIN));


        loginPage
                .openLoginPage();
        loginPage
                .enterUserNameIntoInputLogin(LOGIN);
        loginPage
                .enterPasswordIntoInputPassword(db_util_seleniumUsers.getPassForLogin(LOGIN));
        loginPage
                .clickButtonLogin();
        homePage
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(CHANGE_TITLE)
                .enterTextInInputBody(createPost.get("textBody"))
                .selectTextInUIDropDownOptions(createPost.get("dropDownOptions"))
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage(createPost.get("textInSuccessMessage"))
                .checkTextInNewTitle(CHANGE_TITLE)
                .checkNotePost()
                .checkStatusPost(createPost.get("statusPost"))
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(CHANGE_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(CHANGE_TITLE)
        ;
    }
}

