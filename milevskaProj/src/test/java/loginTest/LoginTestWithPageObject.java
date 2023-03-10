package loginTest;

import baseTest.BaseTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;
@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    @Test
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

    @Test
    @Parameters(method = "provideParametersForInvalidLogin")
    public void invalidLoginWithParameters(String username, String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(username);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();
        loginPage.checkIsInvalidLoginAssertionDisplays();
        Assert.assertFalse("Button Sign Out is displayed", homePage.headerElement.isButtonSignOutDisplayed());
        Assert.assertTrue("Button SignIn is not displayed", loginPage.isButtonSignInDisplayed());


    }

    public static Object[][] provideParametersForInvalidLogin(){
        return new Object[][]{
                new Object[]{"111", "test"},
                new Object[]{"тест", "пароль"},
                new Object[]{"@user", "!!!!!"}
        };
    }
}


