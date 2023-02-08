package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
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

    protected void enterTextIntoElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void printErrorAndStopTest(Exception e){
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected void selectTextInDropDown(WebElement dropDown, String visibleText){
        try{
            Select select = new Select(dropDown);
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + "was selected in Drop-down");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in Drop Down");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    protected boolean isElementDisplayed(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state){
                message = "Element is displayed";
            }else {
                message = "Element is not displayed";
            }
            logger.info(message);
            return state;
        }catch(Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }




}
