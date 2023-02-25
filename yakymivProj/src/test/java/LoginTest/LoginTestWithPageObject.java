package LoginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
        final static String ERROR_MESSAGE = "Invalid username pasword";

    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameintoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("invalidLogin : login = {0}, password = {1}")
    public void invalidLogin(String userName,String password,String expectedError){
        loginPage
                .openLoginPage()
                .fillingLoginFormWithInvalidCred(userName,password)
                .checkLogInErrorMessage(expectedError);
    }

    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"ivan19","invalid_pass", ERROR_MESSAGE },
                new Object[] {"invalid_userName","valid_pass", ERROR_MESSAGE},
                new Object[] {"invalid_userName","invalid_pass", ERROR_MESSAGE}
        };
    }
}
