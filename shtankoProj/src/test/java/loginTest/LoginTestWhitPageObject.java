package loginTest;
import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginTestWhitPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.isButtonSingOutDisplayed());

    }
    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qqaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickButtonLogin();


    }
}
