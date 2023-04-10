package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import org.junit.Assert;
import pages.HomePage;

public class HomePage_StepDefinitions {
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());
            @Given("^User opens 'Home' page")
            public void userOpensHomePage() {
                homePage.openHomePage();
            }

            @When("^User click on 'MyProfile' button on 'Home' page$")
            public void userClickOnMyProfileButtonOnHomePage() {
                homePage.getHeaderElement().clickOnMyProfileButton();
            }

            @Then("^User sees 'My Profile' avatar$")
            public void userSeesMyProfileAvatar() throws Throwable {
                Assert.assertTrue("Avatar is not displayed", homePage.getHeaderElement().isButtonMyProfileDisplayed());
        }
    }