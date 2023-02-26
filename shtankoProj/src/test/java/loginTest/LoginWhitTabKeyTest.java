package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginWhitTabKeyTest extends BaseTest {
    final String USER_NAME = "qaauto";
    final String PASSWORD = "123456qwerty";
    @Test
    public void validLoginTabKey(){
        loginPage.openLoginPage();
        loginPage.userNameTabKey(2,USER_NAME);
        loginPage.passwordTabKey(1,PASSWORD,1,1);
        homePage.getHeaderElement().isButtonSingOutDisplayed();
        homePage.checkIsRedirectToHomePage();

    }
}
