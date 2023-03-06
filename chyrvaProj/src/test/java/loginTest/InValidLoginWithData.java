package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class InValidLoginWithData extends BaseTest {



    //        2. Написати тест на невалідний логін з декількама наборами данних
    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("inValidLogIn : username = {0}, password = {1}")
    public void inValidLogin(String username, String password) {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(username);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();
        loginPage.checkErrorMessageWithTextForLogIn();


        Assert.assertFalse("Button Sign  Out is not displayed", homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button is displayed", loginPage.isButtonLogInDisplayed());


    }

    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"qaauto", "123456qwert"},
                new Object[]{"qaautoo","123456qwerty"},

        };
    }
}