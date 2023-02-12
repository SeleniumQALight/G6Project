package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void testValidLogin() {

        loginPage.openLoginPage();
        loginPage.typeUserName("qaauto");
        loginPage.typeUserPassword("123456qwerty");
        HomePage homePage = loginPage.clickSignIn();


        Assert.assertTrue("Button is not displayed", homePage.getHeaderElements().isButtonSignOutDisplayed());
    }

}
