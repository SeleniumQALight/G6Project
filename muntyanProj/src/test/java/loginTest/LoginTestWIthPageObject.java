package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWIthPageObject extends BaseTest {

    @Test
    public void validLogin (){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInpuLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed", homePage.isButtonSignOutDisplayed() );
    }


}
