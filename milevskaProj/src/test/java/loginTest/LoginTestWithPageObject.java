package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed", homePage.headerElement.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty!");
        loginPage.clickOnButtonLogin();
        Assert.assertFalse("Button Sign Out is displayed", homePage.headerElement.isButtonSignOutDisplayed());
        Assert.assertTrue("Button SignIn is not displayed", loginPage.isButtonSignInDisplayed());
    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed", homePage.headerElement.isButtonSignOutDisplayed());

    }
}


