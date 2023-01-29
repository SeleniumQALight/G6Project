package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class CommonActionsWithElements {
    WebDriver webDriver;
     Logger logger = Logger.getLogger(getClass());
    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }
    protected void enterTextInToElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + "was inputted in to element");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected void clickOnElement(WebElement webElement){
        try{
            webElement.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }


    }


    protected void printErrorAndStopTest(Exception e){
        logger.error("Can't work with element" + e);
        Assert.fail("Can't work with element" + e);
    }
}
