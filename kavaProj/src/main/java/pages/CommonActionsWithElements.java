package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
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

    protected boolean isElementPresented(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }


    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    protected void selectTextInDropDown(WebElement dropDown, String visibleText) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);

    }


}



