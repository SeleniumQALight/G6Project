package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
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

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected  void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    public static boolean isObjectDisplayed(WebElement webElement){
        try {
            return webElement.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public static boolean isObjectNotDisplayed(WebElement webElement){
        try {
            return webElement.isDisplayed();
        }catch (Exception e){
            return true;
        }
    }
}
