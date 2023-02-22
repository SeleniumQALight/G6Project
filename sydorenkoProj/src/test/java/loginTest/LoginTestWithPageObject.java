package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }
    @Test
    public void validLoginWithButtons() {
        loginPage.openLoginPage();
        loginPage.enterLoginAndPasswordIntoInputsAndClickingOnSignInWithEnter("qaauto", "123456qwerty");

        assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
    }
    @Test
    public void isLoginValidInNewTab() {
        loginPage.openLoginPage();
        loginPage.enterLoginAndPasswordIntoInputsAndClickingOnSignInWithEnter("qaauto", "123456qwerty");
        loginPage.openNewTabWithSameUrl();

        assertTrue("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());

        homePage.getHeaderElement().clickOnSignOutButton();
        loginPage.switchToFirstTabAndRefresh();

        assertTrue("Button is not displayed", loginPage.isButtonSignInDisplayed());
    }

    @Test
    public void inValidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("1123456qwerty");
        loginPage.clickOnButtonLogin();

        assertFalse("Button is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
        assertTrue("Button is not displayed", loginPage.isButtonSignInDisplayed());
        assertTrue("Button is not displayed", (!homePage.getHeaderElement().isButtonSignOutDisplayed() & loginPage.isButtonSignInDisplayed()));
    }

}
