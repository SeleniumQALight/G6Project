package postTest;

import baseTest.BaseTest;
import libs.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostTestDB extends BaseTest {

    private Database mysqlDB;
    private Logger logger = Logger.getLogger(Database.class);

    final String LOGIN = "newqaauto";

    Map<String, String> dataForNewPost = ExcelDriver.getData(configProperties.DATA_FILE(), "createPostData");

    final String POST_TITLE = dataForNewPost.get("postTitle") + Util.getDateAndTimeFormatted();

    final String POST_BODY = dataForNewPost.get("postBody");




    public CreatePostTestDB() throws IOException, SQLException {

    }

    @Test
    public void TC1_createNewPost() throws SQLException, ClassNotFoundException {

        DB_Util_selenium db_util_selenium = new DB_Util_selenium();

        logger.info(db_util_selenium.getPassForLogin(LOGIN));

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(LOGIN);
        loginPage.enterPasswordIntoInputPassword(db_util_selenium.getPassForLogin(LOGIN));
        loginPage.clickButtonLogin();

        homePage
                .getHeaderElement().clickOnCreatePostButton()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectValueInDropdownOptions(dataForNewPost.get("visibility"))
                .selectCheckBoxState(dataForNewPost.get("checkbox"))
                .clickSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkCreatedPostTitle(POST_TITLE)
                .checkCreatedPostNote("Note: This post was written for One Person")
                .checkUniqueMessageDisplayed("Is this post unique? : yes")
                .getHeaderElement().clickMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkUserNameDisplayed(LOGIN)
                .checkPostWasCreated(POST_TITLE)
        ;

    }

    @After
    public void deletePost ()  {

        homePage
                .openHomePage()
                .getHeaderElement().clickMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE)
        ;
    }

}


