package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class RegistrationErrorTest extends BaseTest {
    final static String ERROR_USERNAME = TestData.MESSAGE_1;
    final static String ERROR_EMAIL = TestData.MESSAGE_2;
    final static String ERROR_PASSWORD = TestData.MESSAGE_3;
    final static String COMMA = ",";
    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")

    public void checkErrors(String usrName, String email, String password, String expectedErrors){
        loginPage.openLoginPage()
                .enterUserNameIntoInpuPick_a_username(usrName)
                .enterEmailIntoInputEmail(email)
                .enterPasswordIntoInputCreate_a_password(password)
                .clickOnSign_up_for_OurApp();
        loginPage.checkRegistrationErrorsMessages(expectedErrors)
        ;
    }
    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "dfg", "re", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"tr", "df@ee.com", "re", ERROR_USERNAME + COMMA + ERROR_PASSWORD}

        };
    }

}
