package pages;

import libraries.ConfigProperties;
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

public class CommonActionWithElements {
    protected WebDriver webDriver; //protected makes this element available for classes in other packages.
    Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    public CommonActionWithElements(WebDriver webDriver) {
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
            logger.info(text + " is entered into field "+getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info( name +" element is clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xpath) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(xpath)));
            logger.info("Element is clicked ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            String message;
            if (state) {
                message = getElementName(element) + " element is displayed";
            } else {
                message = getElementName(element) + " element is not displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementDisplayed(String locator) {
        try {
           return isElementDisplayed(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected void forTextComparing(String expectedText, WebElement webElement) {
        Assert.assertEquals("Text does not mach", expectedText, webElement.getText());
            logger.info(expectedText + " found its match!");
    }

    protected void selectTextInDropDown(WebElement dropDown, String visibleText) {
        try {
            Select select = new Select(dropDown);// saves in var list of options from dropDown.
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected void selectValueFromDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected inDropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement){
        try{
            return webElement.getAccessibleName();
        }catch(Exception e){
            return  "";
        }

    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }
//-------------------
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

    public void usersPressesKeyWordTime(String keys, int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(keys).build().perform();
        }

    }

    public void userOpensNewTab() {
        ((JavascriptExecutor)webDriver).executeScript("window.open('https://qa-complexapp.onrender.com/')");
        webDriverWait15.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

    public void userOpensNewTab(int num) {
        ((JavascriptExecutor)webDriver).executeScript("window.open('https://qa-complexapp.onrender.com/')");
        webDriverWait15.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(num));
    }

    public void switchBackToOriginTab(){
        String firstWindow = webDriver.getWindowHandles().iterator().next();
        webDriver.switchTo().window(firstWindow);
    }

    public void userRefreshTab(){
        webDriver.navigate().refresh();
    }


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
//WebElement.sendKeys(PAGE_DOWN);
//
//—————————-
//    скрол до елемента з javaScript
//
//            webElement = driver.findElement(By.xpath("bla-bla-bla"));
//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
}
