package loginTest;

import BaseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)

public class NotValidLoginMultiData extends BaseTest {

    final static String EXPECTED_ERROR = "Invalid username pasword";
//    String expectedError = EXPECTED_ERROR;

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, password = {1}")
    public void checkErrors(String userName, String password, String expectedError) {  //, String expectedError
        loginPage
                .openLoginPage()
                .enterUsernameAndPassword(userName, password)
                .checkSignInErrorMessageIsVisible() // <- expectedError
                .checkAlertMessageContainText(EXPECTED_ERROR)
        ;
    }
    public static Object[][] provideParameters() {                                      // варіанти різних наборів даних
        return new Object[][] {
                new Object[] {"tr", "ttt", EXPECTED_ERROR},
                new Object[] {"дбк", "ttt", EXPECTED_ERROR}

        };
    }


}