package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button isn't displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button isn't displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaautoInvalidLogin");
        loginPage.enterPasswordIntoInputPassword("12345qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button isn't displayed", loginPage.isButtonLoginDisplayed());
        Assert.assertFalse("Button is displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    @Parameters(method = "provideParameters")
    public void invalidLoginWithParam(String userName, String password) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(userName);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button isn't displayed", loginPage.isButtonLoginDisplayed());
        Assert.assertFalse("Button is displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"testTest", " "},
                new Object[]{"   ", "??"}
        };
    }
}
