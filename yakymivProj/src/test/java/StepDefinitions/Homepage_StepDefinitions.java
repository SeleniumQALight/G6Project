package StepDefinitions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;

public class Homepage_StepDefinitions {
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("^User sees Sign Out button$")
    public void user_sees_Sign_Out_button() throws Throwable {
        homePage.checkIsRedirectHomePage();
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