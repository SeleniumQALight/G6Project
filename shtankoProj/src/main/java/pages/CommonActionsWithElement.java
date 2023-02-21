package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;




public class CommonActionsWithElement {
    //по замовчуванню вебдрайвер наслідується тільки в рамках пекеджа
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);

        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }
    protected void enterTextInToElement(WebElement webElement, String text){
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + "was inputted in to element" + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
           webElement.click();
           logger.info(getElementName(webElement) + "Element was clicked");
        }catch (Exception e){
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
    protected boolean isElementDisplayed(WebElement webElement){
        //цей метод ми використовуємо для перевірки чи є вебелемент на сторінці чи його немає(алерти, поп-апп та ін..)
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = getElementName(webElement) + "Element is displayed";
            }else {
                message = getElementName(webElement) + "Element is not displayed";
            }
            logger.info(message);
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }
    protected boolean isElementDisplayed(String alertLocator){
        try {
            WebElement element = webDriver.findElement(By.xpath(alertLocator));
            return isElementDisplayed(element);
        }catch (Exception e){
            logger.info("Element ai not displayed");
            return false;
        }
    }
    protected void selectTextInDropDown(WebElement dropDown, String visibleText){
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + "was select in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + "was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInUIDropDown(WebElement dropDown, String choiseTextUI){
        try {
            dropDown.click();
            dropDown.findElement(getXpathFindElementWithTextContains(choiseTextUI)).click();
            logger.info(choiseTextUI + "was selected in UI DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
            throw e;
        }
    }
    private By getXpathFindElementWithTextContains(String choiseTextUI) {
        return By.xpath("//*[contains(text(),\"" + choiseTextUI + "\")]");
    }
    private String getElementName(WebElement webElement){
        try{
            return webElement.getAccessibleName();//відображаємо назву елемента
        }catch (Exception e){
            return ""; //як що не знайде елемент поверне пустоту
        }
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

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

    public void userEnterText(String text){
        Actions actions = new Actions(webDriver);
        actions.sendKeys(text).perform();
    }

    public void usersPressesKeyTime(Keys keys, int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(keys).build().perform();
        }

    }

    public void userOpensNewTab() {
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

    public void switchToPreviousTabAndRefreshPage() {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        if (tabs.size() > 1) {
            webDriver.switchTo().window(tabs.get(tabs.size() - 2));
            webDriver.navigate().refresh();
        } else {
            logger.info("Switch to previous tab as there is only one tab open");
        }
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
//WebElement.sendKeys(Keys.PAGE_DOWN);
//
//—————————-
//    скрол до елемента з javaScript
//
//            webElement = driver.findElement(By.xpath("bla-bla-bla"));
//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);


}
