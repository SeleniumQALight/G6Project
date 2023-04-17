package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;

public class Hooks {
    DriverHelper driverHelper = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());

    ApiHelper apiHelper = new ApiHelper();

    @Before(order = 0)                     // Before - cucumber бібліотека. Це буде загальний Before
    public void setUp() {
          driverHelper.createWebDriver();
          logger.info("");
    }
    @After(order = 0)                // Afte - cucumber бібліотека. Загальний After
    public void tearDown() {
        driverHelper.closeBrowser();
    }

    @Before(value = "@BeforeDeletingAllPostsForDefaultUser", order = 100)    //спеціальні теги для тестів, a order - показник, що показує кого, серед таких ордерів, запускати раніше
    @After(value = "@AfterDeletingAllPostsForDefaultUser", order = 50)
    public void deleteAllPostsForDefaultUser() {
       apiHelper.deletePostsTillPresent(TestData.VALID_LIGIN, TestData.VALID_PASSWORD);
    }

}
