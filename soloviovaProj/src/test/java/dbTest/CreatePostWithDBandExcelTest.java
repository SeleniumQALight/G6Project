package dbTest;

import baseTest.BaseTest;
import libraries.DB_SeleniumUtil;
import libraries.ExcelDriver;
import libraries.Util;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static pages.CommonActionWithElements.*;

public class CreatePostWithDBandExcelTest extends BaseTest {
    final String LOGIN = "newqaauto";
    final DB_SeleniumUtil db_seleniumUtil = new DB_SeleniumUtil();
    final Map<String, String> dataToCreatePost = ExcelDriver.getData(configProperties.DATA_FILE(), "createPost");
    final String POST_TITLE = dataToCreatePost.get("post_title") + Util.getDateAndTimeFormatted();

    public CreatePostWithDBandExcelTest() throws IOException {
    }

    @Test
    public void TC1_createNewPost() throws IOException, SQLException, ClassNotFoundException {
        loginPage.openLoginPage()
                .enterUserNameIntoInputLogin(LOGIN)
                .enterPasswordIntoInputPassword(db_seleniumUtil.getLoginPassword(LOGIN))
                .clickOnButtonLogIn()
        ;
        homePage
                .checkIsRedirectToHomePage()
                .getHeaderElements().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(dataToCreatePost.get("body_title"))
                .checkboxState(dataToCreatePost.get("stateCheck"))
                .selectValueInDropDownOptions(dataToCreatePost.get("dropdownValue"))
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInTitleElement(POST_TITLE)
                .checkIfPostUnique(dataToCreatePost.get("stateCheck"))
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)
        ;
    }
}
