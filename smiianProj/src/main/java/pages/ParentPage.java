package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected  void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }


}
