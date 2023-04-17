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


@Before(order = 0)
    public void setUp(){
driverHelper.createWebDriver();
logger.info("");
    }
    @After(order = 0)
    public void tearDown(){
driverHelper.closeBrowser();
    }

    @Before(value = "@BeforeDeletingAllPostsForDefaultUser", order = 100)
    @After(value = "@AfterDeletingAllPostsForDefaultUser", order = 50)
    public void deleteAllPostsForDefaultUser(){
apiHelper.deletePostsTillPresent(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
    }
}
