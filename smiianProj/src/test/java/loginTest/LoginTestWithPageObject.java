package loginTest;


import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static pages.CommonActionWithElements.configProperties;



@Epic("Allure examples")      // для додавання метафайлів в авто репорт на рівні репорта?
@Feature("Junit 4 support")   // для додавання метафайлів в авто репорт


public class LoginTestWithPageObject extends BaseTest {

 @Description("Some detailed test description")  // для додавання метафайлів в авто репорт на рівні кейса
 @Link("https://example.org")                    //
 @Link(name = "allure", type = "mylink")         //
 @Issue("123")                                   //
 @Issue("432")                                   //
 @Severity(SeverityLevel.CRITICAL)               //
 @Story("Base support for bdd annotations")      // для додавання метафайлів в авто репорт на рівні кейса

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
