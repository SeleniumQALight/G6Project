package postTest;

import baseTest.BaseTest;
import dbTest.DBTest;
import libs.DB_seleniumUsers;
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

public class CreatePostWithExel extends BaseTest {
    private String postTitle;
    private Logger log = Logger.getLogger(DBTest.class);

    @Test
    public void dataFromTable() throws IOException, SQLException, ClassNotFoundException {
        final String LOGIN = "newqaauto";
        Map<String, String> dataForCreatePost = ExcelDriver.getData(configProperties.DATA_FILE(), "postTest");
        postTitle = dataForCreatePost.get("title") + Util.getDateAndTimeFormatted();
        DB_seleniumUsers db_seleniumUsers = new DB_seleniumUsers();


        loginPage
                .openLoginPage();
        loginPage
                .enterUserNameIntoInputLogin(LOGIN);
        loginPage
                .enterPasswordIntoInputPassword(db_seleniumUsers.getPassForLogin(LOGIN));
        loginPage
                .clickOnButtonLogin();
        homePage
                .openHomePage()
                .headerElement.clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(postTitle)
                .enterTextInInputBody(dataForCreatePost.get("body"))
                .selectTextInDropDownOptions(dataForCreatePost.get("dropdown"))
                .checkboxState(dataForCreatePost.get("state"))
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(postTitle);
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(postTitle);
    }
}
