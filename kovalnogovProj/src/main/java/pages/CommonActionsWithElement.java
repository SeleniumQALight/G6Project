package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElement {
    protected Logger logger = Logger.getLogger( getClass() );
    protected WebDriver webDriver;

    public CommonActionsWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    protected void typeTextToElement(WebElement element, String text){
        try {
            element.clear();
            element.sendKeys(text);
            logger.info("Text "+ text+ "was typed to "+ element.toString());
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    protected void click(WebElement element){
        try{
            element.click();
        }catch (Exception e){
            printErroAboutElementAndStopTest(e);
        }
    }
    protected void printErroAboutElementAndStopTest(Exception e){
        logger.error("Can not work with element "+e.getMessage() );
        Assert.fail("Can not work with element  " + e.getMessage() );
    }
}
