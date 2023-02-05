package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;


public class InvalidLoginWithPageObject extends BaseTest {

    @Test

    public void invalidLogin(){

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaaut");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickButtonLogin();

        Assert.assertTrue("User is logged in with incorrect credentials", (loginPage.isErrorMessageDisplayed()) & (loginPage.isSignInButtonDisplayed()));





    }
}
