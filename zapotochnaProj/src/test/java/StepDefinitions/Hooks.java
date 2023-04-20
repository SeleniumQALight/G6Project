package StepDefinitions;

//клас де будуть зберігатись всі бефоре і афтер секції

import api.ApiHelper;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;
import org.apache.log4j.Logger;

public class Hooks {

    DriverHelper driverHelper = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Before(order = 0)
    //якщо кукумбер  бачить декілька бефоре\афтер, то спочатку запустить бефор з 0 , а далі по зростанню .

    public void setUp() {
        driverHelper.createWebDriver();
        logger.info("");
    }

    @After(order = 0)  //якщо кукумбер  бачить декілька бефоре\афтер, то спочатку запустить афтер по спаданню .
    public void tearDown() {
        driverHelper.closeBrowser();
    }


    //anotation from cucumber
    @Before(value = "@BeforeDeletingAllPostsForDefaultUser", order = 100)
    @After(value = "@AfterDeletingAllPostsForDefaultUser", order = 50)

    public void deleteAllPostsForDefaultUser() {
        apiHelper.deletePostsTillPresent();
    }


}
