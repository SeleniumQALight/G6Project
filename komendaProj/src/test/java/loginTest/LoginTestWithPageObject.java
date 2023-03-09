package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class LoginTestWithPageObject extends BaseTest {
    final static String ERROR_MESSAGE_LOGIN_PASSWORD ="Invalid username pasword";
    final static String USERNAME_EMPTY = "";
    final static String USERNAME_VALID = TestData.VALID_LOGIN;
    final static String PASSWORD_EMPTY ="";
    final static String PASSWORD_INVALID = TestData.INVALID_PASSWORD;

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is  not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed()); // якщо метод homePage.isButtonSignOutDisplayed поверта True  а цей метод Assert.assertTrue очікує True значить тест зелений. Якщо метод homePage.isButtonSignOutDisplayed поверта False  а цей метод Assert.assertTrue очікує True то вони не співпадають тест маркається в червоний колір і виводиться повідомлення "Button is  not displayed"

    }
    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is  not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed()); // якщо метод homePage.isButtonSignOutDisplayed поверта True  а цей метод Assert.assertTrue очікує True значить тест зелений. Якщо метод homePage.isButtonSignOutDisplayed поверта False  а цей метод Assert.assertTrue очікує True то вони не співпадають тест маркається в червоний колір і виводиться повідомлення "Button is  not displayed"

    }
    @Test
    public  void inValidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("X123456qwerty");
        loginPage.clickOnButtonLogin();

//        Assert.assertFalse("Button is displayed ", homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button is not displayed", loginPage.getHeaderElement().isButtonSignInDisplayed());
    }


    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void inValidLoginMultipleDatasets(String userName, String password, String expectedError) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(userName);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();
        loginPage.checkLoginInErrorMessage(expectedError);
    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{USERNAME_EMPTY, PASSWORD_EMPTY, ERROR_MESSAGE_LOGIN_PASSWORD},
                new Object[]{USERNAME_VALID, PASSWORD_INVALID, ERROR_MESSAGE_LOGIN_PASSWORD},
                new Object[]{"", "123456qwerty", ERROR_MESSAGE_LOGIN_PASSWORD}
        };
    }
}