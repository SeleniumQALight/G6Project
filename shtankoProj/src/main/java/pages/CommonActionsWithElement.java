package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;




public class CommonActionsWithElement {
    //по замовчуванню вебдрайвер наслідується тільки в рамках пекеджа
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    public CommonActionsWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }
    protected void enterTextInToElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + "was inputted in to element");
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
    protected boolean isElementDisplayed(WebElement webElement){
        //цей метод ми використовуємо для перевірки чи є вебелемент на сторінці чи його немає(алерти, поп-апп та ін..)
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
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

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }


}
