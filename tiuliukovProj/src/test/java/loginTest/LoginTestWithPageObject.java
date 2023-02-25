package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage()
                .enterUserNameIntoInputLogin(TestData.VALID_LOGIN)
                .enterPasswordIntoInputPassword(TestData.VALID_PASSWORD)
                .clickOnButtonSignIn()
                .checkIsUserLoggedIn();
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage()
                .enterUserNameIntoInputLogin(TestData.VALID_LOGIN)
                .enterPasswordIntoInputPassword(TestData.INVALID_PASSWORD)
                .clickOnButtonSignIn()
                .checkIsUserNotLoggedIn();
    }
}
