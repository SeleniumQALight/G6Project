package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {

    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізація елементів з FindBy.  це патерн почитати додатково
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void enterTextInToElement(WebElement webElement, String text) {

        try {

            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement)); //дочекатись поки інпут буде показаний, а вже потім щось з ним робити

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
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
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
