package loginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin(){

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickButtonLogin();
        //homePage.checkIsRedirectToHomePage();
        homePage.getHeaderElement().isButtonSignOutDisplayed();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

    }

    @Test

    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidation = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidation.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidation.get("pass"));
        loginPage.clickButtonLogin();
        //homePage.checkIsRedirectToHomePage();
        homePage.getHeaderElement().isButtonSignOutDisplayed();

        Assert.assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

    }
}
