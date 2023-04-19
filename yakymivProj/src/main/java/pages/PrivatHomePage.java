package pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivatHomePage extends ParentPrivatPage {
    private final String rateBuy = ".//*[@id='%s_buy']";
    private final String rateSell = ".//*[@id='%s_sell']";
    Logger logger = Logger.getLogger(getClass());

    public PrivatHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public PrivatHomePage openHomePage() {
        try {
            webDriver.get(base_url);
            logger.info("Home Page was opened");
        } catch (Exception e) {
            logger.error("Cannot open Home Page" + e);
            Assert.fail("Cannot open Home Page" + e);
        }
        return this;
    }

    public double getCurrencyBuyRate(String currency) {
        logger.info("Got buy rate of " + currency);
        WebElement element = webDriver.findElement(By.xpath(String.format(rateBuy, currency)));
        return Double.parseDouble(element.getText().trim());
    }

    public double getCurrencySellRate(String currency) {
        logger.info("Got sell rate of " + currency);
        WebElement element = webDriver.findElement(By.xpath(String.format(rateSell, currency)));
        return Double.parseDouble(element.getText().trim());
    }
}

