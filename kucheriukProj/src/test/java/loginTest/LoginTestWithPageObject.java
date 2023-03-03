package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("qwerty123456");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                loginPage.isButtonSignInDisplayed());
        Assert.assertFalse("Button is displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void checkFormRegistration(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoRegistrationField("tr");
        loginPage.enterEmailIntoEmailField("test.com");
        loginPage.enterPasswordIntoPasswordRegistrationField("123");
        loginPage.checkErrorCountMessage(3);

        Assert.assertTrue("Name field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("Username must be at least 3 characters."));
        Assert.assertTrue("Email field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("You must provide a valid email address."));
        Assert.assertTrue("Password field validation error is not displayed",
                loginPage.isFieldValidationErrorIsDisplayed("Password must be at least 12 characters."));
    }

    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("inValidLoginWithParameters: login = {0}, password = {1}")
    public void invalidLoginWithParameters(String login, String password){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(login);
        loginPage.enterPasswordIntoInputPassword(password);
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                loginPage.isButtonSignInDisplayed());
        Assert.assertFalse("Button is displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }
    public static Object[][] provideParameters() {
        return new Object[][]{
                new Object[]{"ttt", "rgsrth"},
                new Object[]{"dfrgrst", "regeear"},
        };
    }
}
