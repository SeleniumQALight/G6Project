package newTabTest;

import baseTest.BaseTest;
import org.junit.Test;

import static org.openqa.selenium.Keys.F5;

public class NewTabTest extends BaseTest {
    @Test
    public void newTabLogInTest() {
        loginPage.openLoginPage();
        loginPage.usersPressesKeyTabTime(2);
        loginPage.usersPressesKeyWordTime("qaauto", 1);
        loginPage.usersPressesKeyTabTime(1);
        loginPage.usersPressesKeyWordTime("123456qwerty", 1);
        loginPage.usersPressesKeyTabTime(1);
        loginPage.usersPressesKeyEnterTime(1);
        homePage.checkIsRedirectToHomePage();
        homePage.usersPressesKeyTabTime(1);
        homePage.userOpensNewTab(1);
        homePage.checkIsRedirectToHomePage();
        loginPage.usersPressesKeyTabTime(5);
        loginPage.usersPressesKeyEnterTime(1);
        loginPage.isSignInButtonDisplayed();
        homePage.switchBackToOriginTab();
        homePage.userRefreshTab();
        loginPage.isSignInButtonDisplayed();
    }
}
