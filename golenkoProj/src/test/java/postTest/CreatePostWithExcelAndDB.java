package postTest;

import baseTest.BaseTest;
import dbTest.DBTest;
import libs.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreatePostWithExcelAndDB extends BaseTest {
    private Database mysqlDB;
    private Logger logger = Logger.getLogger(DBTest.class);

    final String ONE_PERSON = "One Person";
    Map<String, String> dataForNewPost =
            ExcelDriver.getData(configProperties.DATA_FILE(), "createPost");
    final String MY_POST_TITLE = dataForNewPost.get("postTitle") + Util.getDateAndTimeFormatted();
    final String LOGIN = "newqaauto";

    public CreatePostWithExcelAndDB() throws IOException {
    }

    @Test
    public void TC_createNewPostWithExcel() throws SQLException, ClassNotFoundException {
        DB_seleniumUsers db_seleniumUsers = new DB_seleniumUsers();
        logger.info(db_seleniumUsers.getPassForLogin(LOGIN));

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(LOGIN);
        loginPage.enterPasswordIntoInputPassword(db_seleniumUsers.getPassForLogin(LOGIN));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());


        homePage
                .getHeaderElement().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(MY_POST_TITLE)
                .enterTextInInputBody(dataForNewPost.get("bodyText"))
                .selectTextInDropDownByUIOptions(dataForNewPost.get("dropDownValue"))
                .setCheckboxSelected(dataForNewPost.get("checkboxState"))
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTitleIsDisplayed(MY_POST_TITLE)
                .checkNoteIsDisplayed()
                .checkSelectedValueIsDisplayed(ONE_PERSON)
                .checkTextInSuccessMessage("New post successfully created.")
                .checkPostIsUnique()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsCorrectLoginDisplayed(LOGIN)
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(MY_POST_TITLE);
    }


    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(MY_POST_TITLE);

    }

}
