package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;


public class HomePage_StepDefinitions {

    HomePage homePage=new HomePage(DriverHelper.getWebDriver());


    @Then("^User is logged in and sees the 'Sign out' button$")
    public void userIsLoggedInAndSeesTheSignOutButton() {
        homePage.checkIsRedirectToHomePage();
    }


    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        homePage.openHomePage();
    }

    @When("^User clicks on 'MyProfile' button on 'Home' page$")
    public void userClicksOnMyProfileButtonOnHomePage() {
        homePage.getHeaderElement().clickOnMyProfileButton();
    }
}