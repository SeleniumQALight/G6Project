package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    protected Logger logger = Logger.getLogger( getClass() );
    protected WebDriver webDriver;


    public ParentPage( WebDriver webDriver ) {
        this.webDriver = webDriver;
    }



    protected void printErroAboutElementAndStopTest(Exception e){
        logger.error("Can not work with element "+e.getMessage() );
        Assert.fail("Can not work with element  " + e.getMessage() );
    }
}
