package loginTest;


import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

 @Test
    public void validLogin() {
     loginPage.openLoginPage();
     loginPage.enterUserNameIntoInputLogin("qaauto");
     loginPage.enterPasswordIntoInputpassword("123456qwerty");
     loginPage.clickOnButtonLogin();

     Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed());

 }
}
