package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is  not displayed", homePage.isButtonSignOutDisplayed()); // якщо метод homePage.isButtonSignOutDisplayed поверта True  а цей метод Assert.assertTrue очікує True значить тест зелений. Якщо метод homePage.isButtonSignOutDisplayed поверта False  а цей метод Assert.assertTrue очікує True то вони не співпадають тест маркається в червоний колір і виводиться повідомлення "Button is  not displayed"

    }
    @Test
    public  void inValidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoPassword("X123456qwerty");
        loginPage.clickOnButtonLogin();

       Assert.assertFalse("Button is displayed ", homePage.isButtonSignOutDisplayed());
       Assert.assertTrue("Button is not displayed", loginPage.isButtonSignInDisplayed());
    }

}
