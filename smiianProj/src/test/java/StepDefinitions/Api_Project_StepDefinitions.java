package StepDefinitions;

import api.ApiHelper;
import apiTest.ApiProject;
import cucumber.api.java.en.Given;
import libs.TestData;


public class Api_Project_StepDefinitions {
    ApiProject apiProject = new ApiProject();



    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")         //  (\d+) та '(.*)' - це Regex, регулярні вирази
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts    //  (\d+) - чилА, '(.*)' - стрінги
            , String userName, String password) {


    }

    @Given("User sends a request to PrivatBank to receive the exchange rate")
    public void user_Sends_A_Request_To_PrivatBank_To_Receive_The_Exchange_Rate() {
//        apiProject........
    }
}
