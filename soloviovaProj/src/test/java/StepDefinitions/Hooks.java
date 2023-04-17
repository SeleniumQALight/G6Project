package StepDefinitions;


import api.APIHelper;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libraries.DriverHelper;
import libraries.TestData;
import org.apache.log4j.Logger;


public class Hooks {
    DriverHelper driverHelper = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());
    APIHelper apiHelper = new APIHelper();

    @Before(order = 0)
    public void setUp() {
        driverHelper.creteWebDriver();
        logger.info("");
    }

    @After(order = 0)
    public void tearDown() {
        driverHelper.closeBrowser();
    }

    @Before(value = "@BeforeDeletingAllPostsForDefaultUser", order = 100)
    //value and order to run these Before and After for particular test
    @After(value = "@AfterDeletingAllPostsForDefaultUser", order = 50)
    public void deleteAllPostsForDefaultUser() {
        apiHelper.deletePostsTillPresent(TestData.VALID_LOGIN, TestData.VALID_PAsSWORD);
    }
}
