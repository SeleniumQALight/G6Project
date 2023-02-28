package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.HomePage;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithParams extends BaseTest {

    public static final String INVALID_USERNAME_PASWORD = "Invalid username pasword";

    @Test
    @Parameters(method = "credentialsParameters")
    @TestCaseName("Credentials : login = {0}, password = {1}")
    public void testInvalidLogin(String userName, String password) {
        loginPage.openLoginPage()
                .typeUserName(userName)
                .typeUserPassword(password)
                .signInAndCheckSignInErrorAlert(INVALID_USERNAME_PASWORD);
    }

    public static Object[][] credentialsParameters() {
        return new Object[][]{
                new Object[]{"qa", "qwe"},
                new Object[]{"qaauto", "qwe"},
                new Object[]{"q", "123456qwerty"}
        };
    }
}
