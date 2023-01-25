package loginTest;
import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWhitPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
    }
}
