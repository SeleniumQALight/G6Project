package pages;

import libs.ConfigProperties;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PPCommonActionWithElements {
    protected WebDriver webDriver;
    Logger loggerExam = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    public PPCommonActionWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
    }



//    protected void enterTextIntoElement(WebElement webElement, String text) {
//        try {
//            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
//            webElement.clear();
//            webElement.sendKeys(text);
//            loggerExam.info(text + " was inputted into element " + getElementName(webElement));
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
//    }







    protected void printErrorAndStopTest (Exception e) {
        loggerExam.error("Can't work with element" + e);
        Assert.fail("Can't work with element" + e);
    }

}
