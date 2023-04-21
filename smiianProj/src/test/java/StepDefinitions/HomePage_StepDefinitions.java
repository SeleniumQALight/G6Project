package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.elements.HeaderElement;


public class HomePage_StepDefinitions {
//    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());
    HeaderElement headerElement = new HeaderElement(DriverHelper.getWebDriver());


    @Given("^User open 'Home' page$")
    public void userOpenHomePage() {
        homePage.openHomePage();
    }

    @When("^User opens 'MyProfile' button on 'Home' page$")
    public void userClicksOnMyProfileButtonOnHomePage() {
        homePage.getHeaderElement().clickOnMyProfileButton();
    }

    @Then("^User sees users account avatar$")
    public void user_Sees_Users_Account_Avatar() {
        headerElement.checkIsAvatarDisplayed();
    }
}
