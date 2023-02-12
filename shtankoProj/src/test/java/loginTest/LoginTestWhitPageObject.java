package loginTest;
import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import pages.elements.HeaderElement;

public class LoginTestWhitPageObject extends BaseTest {
    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    private HeaderElement headerElement = new HeaderElement(webDriver);
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSingOutDisplayed());

    }
}
