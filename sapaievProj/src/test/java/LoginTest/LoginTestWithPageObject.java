package LoginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.CommonActionsWithElements;
import pages.elements.HeaderElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static pages.CommonActionsWithElements.configProperties;


@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {


    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Test
    // если мы хотим пропустить какой-то тест, то можем поставить такую аннотацию. И мавен его поставит в статистику как пропущенный. @Ignore
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto123");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }



    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String,String> dataValidLogin=
                ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin(dataValidLogin.get("login"));
        loginPage.enterPasswordIntoInputPassword(dataValidLogin.get("pass"));
        loginPage.clickOnButtonLogin();
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }


    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwertwwwy");
        loginPage.clickOnButtonLogin();
        Assert.assertFalse("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignInDisplayed());


    }


    @Test
    public void checkTextErrorInFieldRegister() {
        loginPage.openLoginPage();
        loginPage
                .enterUserNameIntoInputUserNameForRegister("tr")
                .enterEmailIntoInputEmailForRegister("test.com" )
                .enterPasswordIntoInputPasswordForRegister("123")
                .checkSizeOfErrorsList(3)
                .checkUserNameError("Username must be at least 3 characters.")
                .checkEmailError("You must provide a valid email address.")
                .checkPasswordError("Password must be at least 12 characters.");
    }


    @Test
    public void checkUserNameTextErrorInFieldRegister() {
        loginPage.openLoginPage();
        loginPage
                .enterUserNameIntoInputUserNameForRegister("tr")
                .checkUserNameError("Username must be at least 3 characters.");
    }

    @Test
    public void checkEmailTextErrorInFieldRegister() {
        loginPage.openLoginPage();
        loginPage
                .enterEmailIntoInputEmailForRegister("test.com")
                .checkEmailError("You must provide a valid email address.");
    }


    @Test
    public void checkPasswordTextErrorInFieldRegister() {
        loginPage.openLoginPage();
        loginPage.enterPasswordIntoInputPasswordForRegister("123")
                .checkPasswordError("Password must be at least 12 characters.");
    }



}
