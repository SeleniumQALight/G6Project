package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.Given;
import libs.DriverHelper;
import libs.TestData;
import pages.HomePage;

public class Api_StepDefinitions {
    final String DEFAULT = "default";     // сетаємо слово ДЕФОЛТ
    private ApiHelper apiHelper = new ApiHelper();


    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")         //  (\d+) та '(.*)' - це Regex, регулярні вирази
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts    //  (\d+) - чилА, '(.*)' - стрінги
            , String userName, String password) {

        if (DEFAULT.equalsIgnoreCase(userName)){    // якщо в userName буде написано ДЕФОЛТ - то кажемо взяти дані з ТестДата
            userName = TestData.VALID_LIGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)){    // якщо в password буде написано ДЕФОЛТ - то кажемо взяти дані з ТестДата
            password = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost(userName, password, "Post from Api " + (i + 1));  // створюємо пости в залежності від вказаної кількості (\d+)
        }

    }
}
