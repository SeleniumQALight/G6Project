package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        loginPage.enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        Assert.assertTrue("Button is not displayed", headerElement.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        loginPage.enterPasswordIntoInputPassword(TestData.INVALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        Assert.assertFalse("Button is displayed", headerElement.isButtonSignOutDisplayed());
        Assert.assertTrue("Button is not displayed", loginPage.isButtonSignInDisplayed());
    }
}
