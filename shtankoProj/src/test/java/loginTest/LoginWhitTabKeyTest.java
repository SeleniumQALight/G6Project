package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginWhitTabKeyTest extends BaseTest {
    final String USER_NAME = "qaauto";
    final String PASSWORD = "123456qwerty";
    @Test
    public void validLoginTabKey(){
        loginPage.openLoginPage();
        loginPage.logoTabKey(1);
        loginPage.userNameTabKey(USER_NAME);
        loginPage.passwordTabKey(PASSWORD);
        loginPage.singInTabKey();
        homePage.getHeaderElement().isButtonSingOutDisplayed();
        homePage.checkIsRedirectToHomePage();

    }
}
