package StepDefinitions;

import api.APIHelper;
import cucumber.api.java.en.Given;
import libraries.DriverHelper;
import libraries.TestData;
import pages.HomePage;

public class Api_StepDefinitions {
    final String DEFAULT = "default";
    private APIHelper apiHelper = new APIHelper();

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String password) {
        if(DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN;
        }
        if(DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PAsSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost(userName, password, "Post form API " + (i + 1));

        }
    }
}
