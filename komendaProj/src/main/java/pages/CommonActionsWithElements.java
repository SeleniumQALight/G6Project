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
import java.util.Locale;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //PageFactory ініціалізує елементи що описані через анотацію findBy
        webDriverWait10 = new WebDriverWait (webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait (webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
    }

    protected void enterTextInToElement(WebElement webElement, String text){
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted in to element" + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info(name + " Element was clicked");
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
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state){
                message = getElementName(webElement) + " Element is displayed";
            }else {
                message =getElementName(webElement) + " Element is not displayed";
            }
            logger.info(message);
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementDisplayed (String xpath){
        try {
            WebElement element = webDriver.findElement(By.xpath(xpath));
            boolean state = element.isDisplayed();
            String message;
            if (state){
                message = "Element is displayed";
            }else {
                message = "Element is not displayed";
            }
            logger.info(message);
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String visibleText){
        try{
            Select select = new Select(dropDown);
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + " was select in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByUI(WebElement dropDown, String textInDD){
        try {
            dropDown.click();
            String selectedXpath = String.format(".//option[text()=\"%s\"]", textInDD);
            webDriver.findElement(By.xpath(selectedXpath)).click();
            logger.info(textInDD+ " was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }


    private String getElementName(WebElement webElement){
        try{
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }
    }

    protected void printErrorAndStopTest(Exception e){
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected boolean isButtonDisplayed(WebElement webElement){
        try {
             return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
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

    protected void selectCheckBoxTrue(WebElement checkBox){
        try {
            if(!checkBox.isSelected()){
                clickOnElement(checkBox);
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectCheckBoxFalse(WebElement checkBox){
        try {
            if (checkBox.isSelected()){
                clickOnElement(checkBox);
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected void selectCheckBox (WebElement checkBox, String targetValue){
        try {
            String checkBoxState = targetValue.toLowerCase();
            if (checkBoxState.equals("check")){
                selectCheckBoxTrue(checkBox);
            } else if (checkBoxState.equals("uncheck")) {
                selectCheckBoxFalse(checkBox);
            }else {
                logger.info("Incorrect target value input check or uncheck ");
                Assert.fail("Incorrect target value input check or uncheck");
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
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
//WebElement.sendKeys(Keys.PAGE DOWN);
//
//—————————-
//    скрол до елемента з javaScript
//
//            webElement = driver.findElement(By.xpath("bla-bla-bla"));
//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);


}
