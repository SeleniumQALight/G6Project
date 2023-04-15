package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.ProfilePage;

public class MyProfilePage_StepDefinitions {
    ProfilePage profilePage= new ProfilePage(DriverHelper.getWebDriver());

    @Then("^User was redirected to 'MyProfile' page$")
    public void userWasRedirectedToProfilePage() {
        profilePage.checkIsRedirectProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts List$")
    public void userSeesPostsInPostsList(int expectedNumberOfPost) {
        profilePage.checkNumberOfPosts(expectedNumberOfPost);
    }
}
