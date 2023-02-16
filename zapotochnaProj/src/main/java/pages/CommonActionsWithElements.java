package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class CommonActionsWithElements {

    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10, webDriverWait15;

    @FindBy(xpath = ".//*[contains(text(),'Приватне повідомлення')]")
    WebElement findTextFromDD;

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

    protected void clickOnElement(String xpath) {  // сюди передаємо локатор у вигляді стрігни. щоб працювати з вебелементом.

        try {
            clickOnElement(webDriver.findElement(By.xpath(String.valueOf(findTextFromDD))));
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
    protected void selectValueByUiInDropDown(WebElement webElement, String textByUi) {
        try {

            clickOnElement(webElement);
            clickOnElement(webDriver.findElement(By.xpath(String.valueOf(findTextFromDD))));


        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //TODO
    protected boolean isElementDisaplayed(String stringvalue) {
    //isElementDisaplayed( );
        return false;
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
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
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
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
}
