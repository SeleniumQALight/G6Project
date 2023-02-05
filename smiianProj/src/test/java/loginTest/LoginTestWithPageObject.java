package loginTest;


import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.CommonActionWithElements;

public class LoginTestWithPageObject extends BaseTest {
 @Test
    public void validLogin() {
     loginPage.openLoginPage();
     loginPage.enterUserNameIntoInputLogin("qaauto");
     loginPage.enterPasswordIntoInputpassword("123456qwerty");
     loginPage.clickOnButtonLogin();

     Assert.assertTrue("SignOut button is not displayed",
             CommonActionWithElements.isObjectDisplayed(webDriver.findElement(By.xpath(".//button[text()='Sign Out']"))));
 }

 @Test
 public void notValidLogin() {
  loginPage.openLoginPage();
  loginPage.enterUserNameIntoInputLogin("AAA");
  loginPage.enterPasswordIntoInputpassword("123456qwerty");
  loginPage.clickOnButtonLogin();

  Assert.assertTrue("SignIn button is displayed",
          CommonActionWithElements.isObjectDisplayed(webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"))));
  Assert.assertTrue("SignOut button is displayed",
          CommonActionWithElements.isObjectNotDisplayed(webDriver.findElement(By.xpath(".//button[text()='Sign Out']"))));

 }

}
