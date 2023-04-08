package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import org.junit.Assert;
import pages.HomePage;

public class HomePage_StepDefinitions {
   HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("^User sees 'My Profile' avatar$")
    public void userSeesMyProfileAvatar() throws Throwable {
        Assert.assertTrue("Avatar is not displayed", homePage.getHeaderElement().isButtonMyProfileDisplayed());
    }
}
