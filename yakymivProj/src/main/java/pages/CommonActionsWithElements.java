package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class CommonActionsWithElements {

    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }

    }

    public void usersPressesKeyTime(Keys keys, int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(keys).build().perform();
        }

    }

    public void userOpensNewTab() {
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }
//
//    метод moveToElement (аналог скрола )
//
//    WebElement element = driver.findElement(By.id("my-id"));
//    Actions actions = new Actions(driver);
//actions.moveToElement(element);
//actions.perform();
//
//—————————-
//    метод скрола з використанням javaScript
//
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("javascript:window.scrollBy(250,350)");
//
//—————————-
//    Емуляція натискання PageDown
//
//WebElement.sendKeys(Keys.DOWN);
//
//—————————-
//    скрол до елемента з javaScript
//
//            webElement = driver.findElement(By.xpath("bla-bla-bla"));
//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);

    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10,webDriverWait15;

    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);


    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
    }

    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + "was inputted in to element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
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

    protected void clickOnElement(String xpath){
        try {
            clickOnElement(webDriver.findElement(By.xpath(xpath)));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        String nameElement = getElementName(webElement);
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = nameElement + " Element is displayed";
            } else {
                message = nameElement + " Element is not displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isButtonDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private String getElementName(WebElement webElement){
        try {
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

    protected void selectTextInDropDown(WebElement dropdown, String visibleText) {
        try {
            Select select = new Select(dropdown);
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropdown, String value) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByUI(WebElement dropdown, String visibleText) {
        try {
            String locatorForDropDown = "//option[contains(text(),'" + visibleText + "')]";
            clickOnElement(dropdown);
            clickOnElement(dropdown.findElement(By.xpath(locatorForDropDown)));
            logger.info(visibleText + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
}
