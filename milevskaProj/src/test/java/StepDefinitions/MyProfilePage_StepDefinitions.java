package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.MyProfilePage;

public class MyProfilePage_StepDefinitions {
    HomePage homePage = new HomePage(DriverHelper.getWebDriver());
    MyProfilePage myProfilePage = new MyProfilePage(DriverHelper.getWebDriver());

    @Then("^User is redirected to 'MyProfile' page$")
    public void userIsRedirectedToMyProfilePage() {
        myProfilePage.checkIsRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts list on 'MyProfile' page")
    public void userSeesPostsInPostsListOnMyProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
