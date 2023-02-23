package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed", homePage.headerElement.isButtonSignOutDisplayed());

    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty!");
        loginPage.clickOnButtonLogin();
        Assert.assertFalse("Button Sign Out is displayed", homePage.headerElement.isButtonSignOutDisplayed());
        Assert.assertTrue("Button SignIn is not displayed", loginPage.isButtonSignInDisplayed());
    }
}


