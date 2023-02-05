package loginTest;


import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.CommonActionWithElements;
import pages.HomePage;

public class LoginTestWithPageObject extends BaseTest {
 @Test
    public void validLogin() {
     loginPage.openLoginPage();
     loginPage.enterUserNameIntoInputLogin("qaauto");
     loginPage.enterPasswordIntoInputpassword("123456qwerty");
     loginPage.clickOnButtonLogin();

     Assert.assertTrue("SignOut button is not displayed",
             homePage.isButtonSignOutDisplayed());
 }

 @Test
 public void notValidLogin() {
  loginPage.openLoginPage();
  loginPage.enterUserNameIntoInputLogin("AAA");
  loginPage.enterPasswordIntoInputpassword("123456qwerty");
  loginPage.clickOnButtonLogin();

  Assert.assertTrue("SignIn button is displayed",
          loginPage.isButtonSignInDisplayed());
  Assert.assertFalse("SignOut button is displayed",
          homePage.isButtonSignOutDisplayed());

 }

}
