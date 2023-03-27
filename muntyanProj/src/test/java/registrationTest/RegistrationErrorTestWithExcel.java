package registrationTest;

import baseTest.BaseTest;
import junitparams.naming.TestCaseName;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.CommonActionsWithElements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.CommonActionsWithElements.configProperties;

@RunWith(Parameterized.class)

public class RegistrationErrorTestWithExcel extends BaseTest {
    String userName, email, password, expectedErrors;

    public RegistrationErrorTestWithExcel(String userName, String email, String password, String expectedErrors) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.expectedErrors = expectedErrors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "registrationErrors").getData();
    }

    @Test
    public void checkErrors(){
        loginPage.openLoginPage()
                .enterUserNameIntoInpuPick_a_username(userName)
                .enterEmailIntoInputEmail(email)
                .enterPasswordIntoInputCreate_a_password(password)
                .clickOnSign_up_for_OurApp();
        loginPage.checkRegistrationErrorsMessages(expectedErrors)
        ;
    }


}
