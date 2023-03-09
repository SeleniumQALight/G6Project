package loginTest;
import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElement.configProperties;

public class LoginTestWhitPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void validLoginWhitExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSingOutDisplayed());

    }
    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qqaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickButtonLogin();


    }
}
