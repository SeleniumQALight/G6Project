package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(JUnitParamsRunner.class)
public class InvalidLoginTest extends BaseTest {
    final static String ERROR_MESSAGE = "Invalid username pasword";
    @Test
    @Parameters(method = "dataParameters")
    @TestCaseName("Data : login = {0}, password = {1}")
    public void InvalidLoginError(String username, String password, String expectedErrors){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(username);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickButtonLogin();
        loginPage.checkLoginErrorMessage(expectedErrors);
    }
    public static Object[][] dataParameters(){
        return new Object[][]{
                new Object[]{"yuy", "qwerty123456",ERROR_MESSAGE},
                new Object[]{"yuy","yuy",ERROR_MESSAGE},
                new Object[]{"qaauto","yuy",ERROR_MESSAGE}
        };

    }
}
