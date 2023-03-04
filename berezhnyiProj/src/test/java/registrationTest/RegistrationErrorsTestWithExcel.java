package registrationTest;

import baseTest.BaseTest;
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

@RunWith(Parameterized.class)
public class RegistrationErrorsTestWithExcel extends BaseTest {
    String userName, email, password, expectedErrors;

    public RegistrationErrorsTestWithExcel(String userName, String email, String password, String expectedErrors) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.expectedErrors = expectedErrors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(CommonActionsWithElements.configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "registrationErrors").getData();
    }

    @Test
    public void checkErrors(){
        loginPage.openLoginPage()
                .enterUserNameIntoRegistrationInput(userName)
                .enterEmailIntoRegistrationInput(email)
                .enterPasswordIntoRegistrationInput(password)
                .checkErrorMessages(expectedErrors);
    }


}
