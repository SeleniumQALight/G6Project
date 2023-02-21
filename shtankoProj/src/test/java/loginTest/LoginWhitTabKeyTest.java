package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginWhitTabKeyTest extends BaseTest {
    final String USER_NAME = "qaauto";
    final String PASSWORD = "123456qwerty";
    @Test
    public void validLoginTabKey(){
        loginPage.openLoginPage();
        loginPage.userNameTabKey(USER_NAME);
        loginPage.passwordTabKey(PASSWORD);
        homePage.getHeaderElement().isButtonSingOutDisplayed();
        homePage.checkIsRedirectToHomePage();

    }
}
