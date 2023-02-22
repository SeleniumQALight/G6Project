package loginTest;
import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWhitPageObject extends BaseTest {

    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSingOutDisplayed());

    }
    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qqaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickButtonLogin();


    }
}
