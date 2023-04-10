package StepDefinitions;

//клас де будуть зберігатись всі бефоре і афтер секції

import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;
import org.apache.log4j.Logger;

public class Hooks {

    DriverHelper driverHelper = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() {
        driverHelper.createWebDriver();
        logger.info("");
    }

    @After
    public void tearDown() {
        driverHelper.closeBrowser();
    }
}
