package loginTest;

import org.junit.Test;

import baseTest.BaseTest;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
    }

}
