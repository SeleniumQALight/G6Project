package StepDefinitions;


import api.ApiHelper;
import cucumber.api.java.en.Given;
import libs.DriverHelper;
import libs.TestData;
import pages.HomePage;

public class API_StepDefinitions {
final String DEFAULT = "default";
private ApiHelper apiHelper = new ApiHelper();
    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName
            ,String password) {
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
apiHelper.createPost(userName,password,"Post from Api " + (i+1));
        }
    }
}
