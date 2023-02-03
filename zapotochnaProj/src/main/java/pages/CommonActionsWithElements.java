package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {

    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізація елементів з FindBy.  це патерн почитати додатково

    }

    protected void enterTextInToElement(WebElement webElement, String text) {

        try {
            webElement.clear();
            webElement.sendKeys(text);

            logger.info(text + " Was inputted in to element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }


    public boolean isButtonSignOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;

        }}


        public boolean isButtonSignInDispalyed () {
            try {
                return webDriver.findElement(By.xpath(".//*[@class =.//*[@class ='btn btn-primary btn-sm']")).isDisplayed();
            } catch (Exception e) {
                return false;

            }


        }


        protected void clickOnElement (WebElement webElement){
            try {
                webElement.click();
                logger.info("Element was clicked");
            } catch (Exception e) {
                printErrorAndStopTest(e);

            }
        }


    }