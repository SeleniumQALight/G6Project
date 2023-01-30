package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void testValidLogin(){

loginPage.openLoginPage();
    }
}
