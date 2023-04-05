package StepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;
import org.apache.log4j.Logger;

public class Hooks {
    DriverHelper driverHelper = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before                     // Before - cucumber бібліотека
    public void setUp() {
          driverHelper.createWebDriver();
          logger.info("");
    }
    @After                // Afte - cucumber бібліотека
    public void tearDown() {
        driverHelper.closeBrowser();

    }

}
