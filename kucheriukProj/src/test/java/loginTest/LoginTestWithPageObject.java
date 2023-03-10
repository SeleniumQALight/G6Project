package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

@Epic("Allure examples")
@Feature("Junit 4 support")
@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }


    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("qwerty123456");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                loginPage.isButtonSignInDisplayed());
        Assert.assertFalse("Button is displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void checkFormRegistration(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationField("tr");
        loginPage.enterEmailIntoEmailField("test.com");
        loginPage.enterPasswordIntoPasswordRegistrationField("123");
        loginPage.checkErrorCountMessage(3);

        Assert.assertTrue("Name field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("Username must be at least 3 characters."));
        Assert.assertTrue("Email field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("You must provide a valid email address."));
        Assert.assertTrue("Password field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("Password must be at least 12 characters."));
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("inValidLoginWithParameters: login = {0}, password = {1}")
    public void invalidLoginWithParameters(String login, String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(login);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                loginPage.isButtonSignInDisplayed());
        Assert.assertFalse("Button is displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }
    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"ttt", "rgsrth"},
                new Object[]{"dfrgrst", "regeear"},
        };
    }
}
