package loginTest;

import BaseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)

public class NotValidLoginMultiData extends BaseTest {

    final static String EXPECTED_ERROR = "Invalid username  pasword";  // Invalid username pasword   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    String expectedError = EXPECTED_ERROR;

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, password = {1}")
    public void checkErrors(String userName, String password) {
        loginPage
                .openLoginPage()
                .enterUsernameAndPassword(userName, password)
                .checkSignInErrorMessageIsVisible()
                .checkSignInErrorMessageContainText(EXPECTED_ERROR)
        ;
    }
    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "ttt"},
                new Object[] {"дбк", "ttt"},
                new Object[] {"/?*()", "ttt"}

        };
    }


}