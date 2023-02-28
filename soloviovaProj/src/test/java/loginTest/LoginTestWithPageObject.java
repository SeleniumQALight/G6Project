package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libraries.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String ERROR_MESSAGE_LOGIN = "Invalid username pasword";
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        loginPage.enterPasswordIntoInputPassword(TestData.VALID_PAsSWORD);
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button is not displayed", headerElements.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaaut");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertFalse("Sign Out button is displayed", headerElements.isButtonSignOutDisplayed());
        Assert.assertTrue("Sign in button is not displayed", loginPage.isSignInButtonDisplayed());
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("logInError : login = {0}, password = {2}")
    public void invalidLoginWithParams(String userName, String password, String expectedMessage){
        loginPage.openLoginPage()
                .enterUserNameIntoInputLogin(userName)
                .enterPasswordIntoInputPassword(password)
                .clickOnButtonLogIn()
                .checkErrorMessageForLogIn(expectedMessage)
        ;
    }
    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"qaauto", "dfg", ERROR_MESSAGE_LOGIN},
                new Object[] {"tr", "123456qwerty",  ERROR_MESSAGE_LOGIN},
        };
    }

}
