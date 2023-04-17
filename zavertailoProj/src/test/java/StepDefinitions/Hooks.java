package StepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriveHelper;
import org.apache.log4j.Logger;

public class Hooks {
    DriveHelper driveHelper = new DriveHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp(){
        driveHelper.crestedWebDriver();
        logger.info("");

    }
    @After
    public void tearDown(){
        driveHelper.closedBrowser();

    }

}
