package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    final String POST_TITLE = "TC2_sivova_" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "TC2_body";

    @Before
    public void CreatePost() {
        loginPage
                .openLoginPage()
                .fillingLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnCreatePostButton()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(POST_BODY)
                .selectValueInDropdownOptions("One Person")
                .clickSavePostButton()
        ;

    }

    @Test
    public void EditPost() {

    }

    @After

    public void DeletePost() {

    }

}
