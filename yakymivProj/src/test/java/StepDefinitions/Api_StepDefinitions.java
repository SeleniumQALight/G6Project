package StepDefinitions;

import API.ApiHelper;
import cucumber.api.java.en.Given;
import libs.TestData;


public class Api_StepDefinitions {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.USERNAME;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost(userName, password, "Post form API " + (i + 1));

        }
    }
}