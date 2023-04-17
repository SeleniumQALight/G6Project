package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class Api_StepDefinitions {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();


    @Given("^Create (\\d+) new post via API for '(.*)' user and '(.*)' password$")
    public void createNewPostViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String username, String password) {
        if (DEFAULT.equalsIgnoreCase(username)){
            username = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost(username, password, "Post from Api" + (i +1));
        }

    }
}
