package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    Logger logger=Logger.getLogger(getClass());
    WebDriverWait webDriverWait10;
    WebDriverWait webDriverWait15;


    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        webDriverWait10=new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15=new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }


    protected void enterTextIntiElement(WebElement webElement, String text){
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text+"was inputted into element");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    protected void printErrorAndStopTest(Exception e){
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }



    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xPath){
        try {
            clickOnElement(webDriver.findElement(By.xpath(xPath)));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }



    protected void selectTextInDropdown(WebElement dropDown, String vivibleText){
        try {
            Select select=new Select(dropDown);
            select.selectByVisibleText(vivibleText);
            logger.info(vivibleText+" was selected in Dropdown");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }



    protected void selectValueInDropdown(WebElement dropDown, String value){
        try {
            Select select=new Select(dropDown);
            select.selectByValue(value);
            logger.info(value+" was selected in Dropdown");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }







    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state= webElement.isDisplayed();
            String message;

            if(state){
                message="Element is displayed";
            }else {
                message="Element is not displayed";
            }

            logger.info(message);
            return state;

        } catch (Exception e) {
            logger.info("element is not displayed");
            return false;
        }

    }




}

