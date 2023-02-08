package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {

    protected  WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізація елементів з FindBy.  це патерн почитати додатково

    }

    protected void enterTextInToElement(WebElement webElement, String text) {

        try {
            webElement.clear();
            webElement.sendKeys(text);

            logger.info(text + " Was inputted in to element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    protected boolean isElementDisplayed(WebElement webElement) {

        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = "Element is Displayed";
            } else {
                message = "Element is not Displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
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

    protected void selectTextInDropDown(WebElement dropDown, String visibleText) {

        try {
            Select select = new Select(dropDown); //даємо селекту всі рядочку з дропдауну.
            select.selectByVisibleText(visibleText);

            logger.info(visibleText + " was selected on dropdown");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }
    protected void selectValueInDropDown(WebElement dropDown, String value) {

        try {
            Select select = new Select(dropDown); //даємо селекту всі рядочку з дропдауну.
            select.selectByValue(value);

            logger.info(value + " was selected on dropdown");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //дз зробити byUi

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }
}
