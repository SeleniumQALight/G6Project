package loginTest;

import baseTest.BaseTest;
import categoties.SmokeTestFilter;

import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.CommonActionsWithElement;
import pages.HomePage;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElement.configProperties;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void testValidLogin() {

        loginPage.openLoginPage();
        loginPage.typeUserName("qaauto");
        loginPage.typeUserPassword("123456qwerty");
        HomePage homePage = loginPage.clickSignIn();


        Assert.assertTrue("Button is not displayed", homePage.getHeaderElements().isButtonSignOutDisplayed());
    }

    @Test
    public void testValidLoginWithExel() throws IOException {
        Map<String,String > dataForValidLogin = ExcelDriver.getMultipleData(configProperties.DATA_FILE(), "validLogOn",1);
        loginPage.openLoginPage();
        loginPage.typeUserName(dataForValidLogin.get("login"));
        loginPage.typeUserPassword(dataForValidLogin.get("pass"));
        HomePage homePage = loginPage.clickSignIn();


        Assert.assertTrue("Button is not displayed", homePage.getHeaderElements().isButtonSignOutDisplayed());
    }

}
