package createNewPostDB;

import baseTest.BaseTest;
import dbTest.DBTest;
import libs.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class CreateNewPostWithDB extends BaseTest {
//    String Title, BodyText, CheckBox, DDText;
final String UserName = "newqaauto";

    private Database mysqlDB;
    private Logger log = Logger.getLogger(DBTest.class);
    Map<String,String> dataForNewPost = ExcelDriver.getData(configProperties.DATA_FILE(),"CreatePost");
    final String TITLE = dataForNewPost.get("Title")+ Util.getDateAndTimeFormatted();

    public CreateNewPostWithDB() throws IOException {
    }

    @Test
    public void createNewPostWithDB() throws SQLException, ClassNotFoundException, IOException {


                homePage.openHomePageDB()
                .getHeaderElement()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage();
        createPostPage.enterTextInInputTitleDB(TITLE)
                .enterTextInInputBody(dataForNewPost.get("BodyText"))
                .selectDesiredCheckBoxStatus(dataForNewPost.get("CheckBox"))
                .selectValueInDropDownOptions(dataForNewPost.get("DDText"))
            .clickOnSavePostButton()
            .checkIsRedirectToPostPage()
            .checkTextInSuccessMessage("New post successfully created.")
            .checkNewTitleIsDisplayed(TITLE)
            .checkIsNoteDisplayedOnPostPage("Note: This post was written for One Person")
            .checkTextisDisplayedInBody("One Person")
            .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()
            .checkPostWasCreated(TITLE)
            .checkUserIsDisplayedOnMyProfilePage(UserName);


    }
    @After
    public void deletePost() throws SQLException, ClassNotFoundException {
        homePage.openHomePageDB()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(TITLE)



        ;
    }

}
