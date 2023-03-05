package invalidRegistrationTest;

import baseTest.BaseTest;
import libraries.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.CommonActionWithElements.configProperties;

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
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH()+"testDataSuit.xls");
        return new SpreadsheetData(inputStream, "registrationErrors").getData();
    }

    @Test
    public void checkErrors(){
        loginPage.openLoginPage()
                .enterUserNameToRegister(userName)
                .enterEmailToRegister(email)
                .enterPasswordToRegister(password)
                .checkRegistrationErrorsMessages(expectedErrors)
        ;
    }


}
