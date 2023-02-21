package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CheckLogOutInTwoOpenTabTest extends BaseTest {
    @Test
    public void logOutInTwoOpenTabTest(){
        homePage.openHomePage()
                .getHeaderElement().checkButtonSingOutIsDisplayed();
        homePage.getUrlHomepage()
                .getHeaderElement().checkButtonSingOutIsDisplayed();
        homePage.getHeaderElement().clickButtonSingOut();
        loginPage.checkSingInButtonIsDisplayed();
        loginPage.switchToPreviousTabAndRefreshPage();
        loginPage.checkSingInButtonIsDisplayed();
    }
}
