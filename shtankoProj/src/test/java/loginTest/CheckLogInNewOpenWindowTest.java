package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CheckLogInNewOpenWindowTest extends BaseTest {
    @Test
    public void checkLogInNewOpenWindow(){
        homePage.openHomePage()
                .getHeaderElement().isButtonSingOutDisplayed();
        homePage.openNewTabHomePage()
                .getHeaderElement().isButtonSingOutDisplayed();
        homePage.getHeaderElement().clickOnMyProfileButton();
    }
}
