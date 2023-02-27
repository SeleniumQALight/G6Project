package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class AssertionTest extends BaseTest {
    @Test

    public void assertionCheck(){
        loginPage.openLoginPage();
        loginPage.fillingRegistationFormWithInvalidCred();
                //.checkIsUsernameAssertionsIsDisplayed();

    }
}
