package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.CommonActionsWithElements;

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
    //@Ignore //пропускає тест
    @Category(SmokeTestFilter.class)
    public void validLogin(){

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaaut");
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
