package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElement {
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriver webDriver;
    WebDriverWait wait10;
    WebDriverWait wait15;

    public CommonActionsWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait10=new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait15=new WebDriverWait(webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(webDriver, this);
    }


    protected  WebElement getWebElement(String xpath){
        WebElement element = null;
        try{
            By elementBy = By.xpath(xpath);
            wait15.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
            element= webDriver.findElement(elementBy);
        }
        catch (Exception e){
            printErroAboutElementAndStopTest( e );
        }

        return element;
    }
    protected void typeTextToElement(WebElement element, String text) {
        wait15.until(ExpectedConditions.visibilityOf(element));
        try {
            element.clear();
            element.sendKeys(text);
            logger.info("Text " + text + "was typed to " + element.toString());
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement element) {
        wait10.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }
    protected void clickOnElement(String xpath) {
        try {
           clickOnElement(webDriver.findElement(By.xpath(xpath)));
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

    protected String getTextDecorationCssProperty(WebElement element) {
        try {
            return element.getCssValue("text-decoration");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
        return "";
    }

    protected void selectTextInDropDownByUi(WebElement dropDown, String text) {
        try {
            clickOnElement(dropDown);
            webDriver.findElement(By.xpath(".//select/option[contains(text(),'" + text + "')]"))
                    .click();
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
