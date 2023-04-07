package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import org.junit.Assert;
import pages.HomePage;

public class HomePage_StepDefinitions {
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("^User sees 'My Profile' avatar$")
    public void user_sees_My_Profile_avatar() throws Throwable {
        Assert.assertTrue("Profile avatar is not displayed", homePage.getHeaderElement().isProfileAvatarDisplayed());
    }

}
