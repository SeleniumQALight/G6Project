package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElement {
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriver webDriver;

    public CommonActionsWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected void typeTextToElement(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info("Text " + text + "was typed to " + element.toString());
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }


    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            String message;
            if (state) {
                message = "Element is displayed";
            } else {
                message = "Element is not displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected String getText(WebElement element) {
        String text = "";
        try {
            text = element.getText();
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
        return text;
    }

    protected void selectTextInDropDownByUi(WebElement dropDown, String text) {
        try {
            //TODO homework create method which will select in dd

        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + "visible text was selected");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + "value was selected");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    protected void printErroAboutElementAndStopTest(Exception e) {
        logger.error("Can not work with element " + e.getMessage());
        Assert.fail("Can not work with element  " + e.getMessage());
    }
}
