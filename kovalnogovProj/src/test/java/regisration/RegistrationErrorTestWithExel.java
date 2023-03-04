package regisration;

import baseTest.BaseTest;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.CommonActionsWithElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import static pages.CommonActionsWithElement.*;

@RunWith(Parameterized.class)
public class RegistrationErrorTestWithExel extends BaseTest {
    private String userName, userEmail, password, expectedErrors;

    public RegistrationErrorTestWithExel(String userName, String userEmail, String password, String expectedErrors) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.expectedErrors = expectedErrors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(fileInputStream, "registrationErrors").getData();
    }

    @Test
    public void checkErrors() {
        loginPage.openLoginPage()
                .typeUserNameForRegistration(userName)
                .typeEmailForRegistration(userEmail)
                .typePasswordForRegistration(password)
                .checkErrorMessageUniversal(expectedErrors);
    }
}
