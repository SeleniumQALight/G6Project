package StepDefinitions;


import api.APIHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libraries.DriverHelper;
import libraries.TestData;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;


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

    @After(order = 100)
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + DriverHelper.getWebDriver().getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) DriverHelper.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");  // Stick it in the report
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.out.println(somePlatformsDontSupportScreenshots.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
    }
}
