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
    public void InvalidLoginError(String username, String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(username);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickButtonLogin();
        loginPage.checkLoginErrorMessage(ERROR_MESSAGE);
    }
    public static Object[][] dataParameters(){
        return new Object[][]{
                new Object[]{"yuy", "qwerty123456"},
                new Object[]{"yuy","yuy"},
                new Object[]{"qaauto","yuy"}
        };

    }
}
