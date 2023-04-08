package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.HomePage;


public class HomePage_StepDefinitions {

    HomePage homePage=new HomePage(DriverHelper.getWebDriver());


    @Then("^User is logged in and sees the 'Sign out' button$")
    public void userIsLoggedInAndSeesTheSignOutButton() {
        homePage.checkIsRedirectToHomePage();
    }
}