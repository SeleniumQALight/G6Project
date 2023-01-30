package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionWithElements {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public CommonActionWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " is entered into field ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected  void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element is clicked");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement element){
        try {
            return element.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }
}
