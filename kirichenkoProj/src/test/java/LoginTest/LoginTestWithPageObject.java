package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed"
                , headerElement.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin(){
        loginPage.fillingLoginFormWithInvalidCred();
        loginPage.checkTextInLoginErrorMessage("Invalid username pasword");

        Assert.assertFalse("Home page is loaded", headerElement.isButtonSignOutDisplayed());
        Assert.assertTrue("Sign in button is not displayed", loginPage.isSignInButtonDisplayed());
    }
}
