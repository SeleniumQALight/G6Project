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
    @Description("Some detailed test description")

    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")

    @Test
    @Category(SmokeTestFilter.class)


    public void validLogin() {

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("p");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button SignOut is not displayed",
                homePage
                        .getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void inValidLogin() {

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin("invalidtext");
        loginPage.enterPasswordIntoInputPassword("invalidtext");
        loginPage.clickOnButtonLogin();


        Assert.assertFalse("Button SignOut is not displayed",
                homePage
                        .getHeaderElement().isButtonSignOutDisplayed());

        Assert.assertTrue("Button SignIn is not displayed", loginPage.isButtonSignInDisplayed());

    }

    //hw 5:
    //TODO
    @Test
    public void invalidLoginRedMessages() {

    }

    @Test
    public void validLoginWithExcel() throws IOException { //throws IOException - означає що тут можуть бути ексепшини. просто повідомлення.
        Map<String, String> dataFirValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLogin(dataFirValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataFirValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button SignOut is not displayed",
                homePage
                        .getHeaderElement().isButtonSignOutDisplayed());
    }

}


