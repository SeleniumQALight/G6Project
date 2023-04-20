package StepDefinitions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;

public class HomePage_StepDefinitions {
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        homePage.openHomePage();
    }

    @Given("Create {int} new posts via API for {string} user and {string} password")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int arg0) {
    }

    @When("^User clicks on 'MyProfile' button on 'Home' page")
    public void userClicksOnMyProfileButtonOnHomePage() {
    }
}
