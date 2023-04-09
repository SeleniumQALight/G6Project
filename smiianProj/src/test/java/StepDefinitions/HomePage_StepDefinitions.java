package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;


public class HomePage_StepDefinitions {
//    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());


    @Given("^User open 'Home' page$")
    public void userOpenHomePage() {
        homePage.openHomePage();
    }

    @When("^User opens 'MyProfile' button on 'Home' page$")
    public void userClicksOnMyProfileButtonOnHomePage() {
        homePage.getHeaderElement().clickOnMyProfileButton();
    }
}
