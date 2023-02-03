package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();


        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());

    }

    @Test
        public void inValidLogin () {

            loginPage.openLoginPage();
            loginPage.enterUserNameIntoLogin("invalid");
            loginPage.enterPasswordIntoInputPassword("----");
            loginPage.clickOnButtonLogin();




        Assert.assertTrue("Button is not displayed", !loginPage.isButtonSignInDispalyed() );




        }


    }
