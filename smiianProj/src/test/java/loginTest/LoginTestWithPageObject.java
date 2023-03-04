package loginTest;


import BaseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import pages.CommonActionWithElements;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {
 @Test
    public void validLogin() {
     loginPage.openLoginPage();
     loginPage.enterUserNameIntoInputLogin("qaauto");
     loginPage.enterPasswordIntoInputpassword("123456qwerty");
     loginPage.clickOnButtonLogin();

     Assert.assertTrue("SignOut button is not displayed",
             headerElement.isButtonSignOutDisplayed());
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
          headerElement.isButtonSignOutDisplayed());

 }

 @Test
 public void validLoginWithExcel() throws IOException {
  Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
  loginPage.openLoginPage();
  loginPage.enterUserNameIntoInputLogin(dataForValidLogin.get("login"));   // до довання Excel файлів в дужках було "qaauto"
  loginPage.enterPasswordIntoInputpassword(dataForValidLogin.get("pass"));  // до довання Excel файлів в дужках було "123456qwerty"
  loginPage.clickOnButtonLogin();

  Assert.assertTrue("SignOut button is not displayed",
          headerElement.isButtonSignOutDisplayed());
 }

}
