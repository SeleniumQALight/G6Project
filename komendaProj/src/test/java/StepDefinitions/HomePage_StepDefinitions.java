package StepDefinitions;

<<<<<<< HEAD
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import org.junit.Assert;
import pages.HomePage;

public class HomePage_StepDefinitions {
   HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Then("^User sees 'My Profile' avatar$")
    public void userSeesMyProfileAvatar() throws Throwable {
        Assert.assertTrue("Avatar is not displayed", homePage.getHeaderElement().isButtonMyProfileDisplayed());
=======
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;
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
>>>>>>> 72baebb147e9415ccca11a319a1783a7d7ad0c04
    }
}
