package loginTest;

import baseTest.BaseTest;
import categoties.SmokeTestFilter;

import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.CommonActionsWithElement;
import pages.HomePage;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElement.configProperties;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Test
    @Category(SmokeTestFilter.class)
   // @Ignore
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
