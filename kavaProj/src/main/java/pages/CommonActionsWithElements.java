package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());


    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted in to element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected void isElementPresented(WebElement element) {
        try {
            Assert.assertTrue("Element is not presented", element.isDisplayed());
            logger.info("Element is presented");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void isElementNotPresented(WebElement element) {
        try {
            logger.info("Element is not presented");
        } catch (Exception e) {
            Assert.assertFalse(element.isDisplayed());
        }

    }


    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);

    }


}



