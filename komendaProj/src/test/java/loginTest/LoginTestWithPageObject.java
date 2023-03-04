package loginTest;

import baseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.CommonActionsWithElements;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {
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

       Assert.assertFalse("Button is displayed ", homePage.getHeaderElement().isButtonSignOutDisplayed());
       Assert.assertTrue("Button is not displayed", loginPage.getHeaderElement().isButtonSignInDisplayed());
    }

}
