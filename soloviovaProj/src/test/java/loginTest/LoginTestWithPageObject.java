package loginTest;

import baseTest.BaseTest;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libraries.ExcelDriver;
import libraries.TestData;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionWithElements.configProperties;

@RunWith(JUnitParamsRunner.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    final static String ERROR_MESSAGE_LOGIN = "Invalid username / password.";
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Test
   // @Ignore
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("ys20230411");
        loginPage.enterPasswordIntoInputPassword("qwerty1234567890");
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button is not displayed", headerElements.isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaaut");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogIn();

        Assert.assertFalse("Sign Out button is displayed", headerElements.isButtonSignOutDisplayed());
        Assert.assertTrue("Sign in button is not displayed", loginPage.isSignInButtonDisplayed());
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("logInError : login = {0}, password = {2}")
    public void invalidLoginWithParams(String userName, String password, String expectedMessage){
        loginPage.openLoginPage()
                .enterUserNameIntoInputLogin(userName)
                .enterPasswordIntoInputPassword(password)
                .clickOnButtonLogIn()
                .checkErrorMessageForLogIn(expectedMessage)
        ;
    }
    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"qaauto", "dfg", ERROR_MESSAGE_LOGIN},
                new Object[] {"tr", "123456qwerty",  ERROR_MESSAGE_LOGIN},
        };
    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String , String > dataForValidLogIn = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogIn.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogIn.get("pass"));
        loginPage.clickOnButtonLogIn();

        Assert.assertTrue("Button is not displayed", headerElements.isButtonSignOutDisplayed());
    }

}
