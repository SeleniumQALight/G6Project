package registrationTest;

import baseTest.BaseTest;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.CommonActionsWithElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@RunWith(Parameterized.class)//вказує що данний тест класс треба зупскати декілька разів з різними пареметрами
public class RegistrationErrorTestWhitExcel extends BaseTest {
    String userName, email,password, expectedErrors;

    //Згенерували конструктор зі всіма змінними які ми написали вищче
    public RegistrationErrorTestWhitExcel(String userName, String email, String password, String expectedErrors) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.expectedErrors = expectedErrors;
    }

    //Метод який йде в ексель і зчитує данні звідти
    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(
                CommonActionsWithElement.configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "registrationErrors")//другий параметр це назва аркуша в Exel
                .getData();
    }

    @Test
    public void checkErrors(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputRegistrationForm(userName)
                .enterEmailIntoInputRegistrationForm(email)
                .enterPasswordIntoInputRegistrationForm(password)
                .checkErrorMessages(expectedErrors);
    }
}
