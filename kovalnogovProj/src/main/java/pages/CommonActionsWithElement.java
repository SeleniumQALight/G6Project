package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
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

public class CommonActionsWithElement {
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriver webDriver;
    protected WebDriverWait wait10;
    protected WebDriverWait wait15;

    public enum CheckBoxState {
        CHECKED,
        UNCHECKED;
    }

    public CommonActionsWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait10 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        wait15 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
        PageFactory.initElements(webDriver, this);
    }


    protected WebElement getWebElement(String xpath) {
        WebElement element = null;
        try {
            By elementBy = By.xpath(xpath);
            wait15.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
            element = webDriver.findElement(elementBy);
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }

        return element;
    }

    protected void typeTextToElement(WebElement element, String text) {
        wait15.until(ExpectedConditions.visibilityOf(element));
        try {
            element.clear();
            element.sendKeys(text);
            logger.info("Text " + text + " was typed to " + getElementName(element));
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement element) {
        wait10.until(ExpectedConditions.elementToBeClickable(element));
        try {
            logger.info(" Element was clicked: " + getElementName(element));
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
                message = getElementName(element) + " Element is displayed";
            } else {
                message = getElementName(element) + " Element is not displayed";
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

    private String getElementName(WebElement element) {
        try {
            return element.getAccessibleName();
        } catch (Exception e) {
            return "";
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

    protected void selectCheckBox(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
            }
            logger.info("Click on checkBox");
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }

    }

    protected void selectCheckBox(WebElement element, CheckBoxState state) {
        try {
            logger.info("Current checkbox state: " + element.isSelected());
            if (state == CheckBoxState.CHECKED && element.isSelected() == false) {
                selectCheckBox(element);
                logger.info("Select checkbox");
            } else if (state == CheckBoxState.UNCHECKED && element.isSelected() == true) {
                selectCheckBox(element);
                logger.info("Deselect checkbox");
            }
        } catch (Exception e) {
            printErroAboutElementAndStopTest(e);
        }
    }

    protected void printErroAboutElementAndStopTest(Exception e) {
        logger.error("Can not work with element " + e.getMessage());
        Assert.fail("Can not work with element  " + e.getMessage());
    }
}
