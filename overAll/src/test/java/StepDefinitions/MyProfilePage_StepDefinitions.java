package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.MyProfilePage;

public class MyProfilePage_StepDefinitions {
    MyProfilePage myProfilePage = new MyProfilePage(DriverHelper.getWebDriver());

    @Then("^User was redirected to 'MyProfile' page$")
    public void userWasRedirectedToProfilePage() {
        myProfilePage.checkIsRedirectToMyProfilePage();
    }


    @And("^User sees (\\d+) posts in Posts list on 'MyProfile' page$")
    public void userSeesPostsInPostsListOnMyProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
