package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CheckLogOutInTwoOpenTabTest extends BaseTest {
    @Test
    public void logOutInTwoOpenTabTest(){
        homePage.openHomePage()
                .getHeaderElement().isButtonSingOutDisplayed();
        homePage.openNewTabHomePage()
                .getHeaderElement().isButtonSingOutDisplayed();
        homePage.getHeaderElement().clickButtonSingOut();
        loginPage.checkSingInButtonIsDisplayed();
        homePage.getHeaderElement().switchToPreviousTab();
        loginPage.checkSingInButtonIsDisplayed();
    }
}
