package postTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;


public class createPostWithExcel extends BaseTest {
    final String myUniqueTitle = ExcelDriver.getData(configProperties.DATA_FILE(), "createPost") + Util.getDateAndTimeFormatted();

    public createPostWithExcel() throws IOException {
    }


    @Test
    public void TC_3_createPostWithExcel() throws IOException {

        Map<String, String> dataFirValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin(dataFirValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataFirValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button SignOut is not displayed",
                homePage
                        .getHeaderElement().isButtonSignOutDisplayed());


        Map<String, String> dataForCreatePost = ExcelDriver.getData(configProperties.DATA_FILE(), "createPost");


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

                .checkIsProfileCorrect(dataFirValidLogin.get("login"))

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

