package loginTest;


import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionWithElements.configProperties;

public class LoginTestWithPageObject extends BaseTest {
 @Test
 @Category(SmokeTestFilter.class)        //  додано для роботи з ТестСьютом
 public void validLogin() {
  loginPage.openLoginPage();
  loginPage.enterUserNameIntoInputLogin("qaauto");
  loginPage.enterPasswordIntoInputPassword("123456qwerty");
  loginPage.clickOnButtonLogin();

  Assert.assertTrue("SignOut button is not displayed",
          headerElement.isButtonSignOutDisplayed());
 }

 @Test
 public void notValidLogin() {
  loginPage.openLoginPage();
  loginPage.enterUserNameIntoInputLogin("AAA");
  loginPage.enterPasswordIntoInputPassword("123456qwerty");
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
  loginPage.enterPasswordIntoInputPassword(dataForValidLogin.get("pass"));  // до довання Excel файлів в дужках було "123456qwerty"
  loginPage.clickOnButtonLogin();

  Assert.assertTrue("SignOut button is not displayed",
          headerElement.isButtonSignOutDisplayed());
 }

}
