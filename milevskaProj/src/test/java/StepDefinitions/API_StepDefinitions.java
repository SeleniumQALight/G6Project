package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import pages.HomePage;
import pages.LoginPage;

public class API_StepDefinitions {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();
    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String username, String password) {
        if (DEFAULT.equalsIgnoreCase(username)){
            username = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_LOGIN;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost(username, password, "Post from Api "+ (i+1));



        }
    }
}
