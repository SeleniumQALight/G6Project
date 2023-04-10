package StepDefinitions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinitions {
    HomePage homePage = new HomePage(DriverHelper.getWebdriver());


    @Then("^User sees 'Signout' button$")
    public void userSeesSignoutButton() {
        homePage.checkIsRedirectToHomePage().getHeaderElement().isButtonSignOutDisplayed();

    }
}
