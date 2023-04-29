package pagesPrivat;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPagePrivat extends BasePage{
    private final String currencyRate = ".//*[@id='%s_%s']";

    public MainPagePrivat(WebDriver webDriver) {
        super(webDriver);
    }
    Logger logger = Logger.getLogger(getClass());

    public MainPagePrivat openMainPage(){
        try {
            webDriver.get(home_url_privat);
            logger.info("Main page was opened");
        } catch (Exception e) {
            logger.error("Cannot open Main page" + e);
            Assert.fail("Cannot open Main page" + e);
        }
        return this;
    }

    public double getCurrencySellRate(String currency) {
        WebElement sell = webDriver.findElement(By.xpath(String.format(currencyRate, currency, "sell")));
        return Double.parseDouble(sell.getText().trim());
    }

    public double getCurrencyBuyRate(String currency) {
        WebElement buy = webDriver.findElement(By.xpath(String.format(currencyRate, currency, "buy")));
        return Double.parseDouble(buy.getText().trim());
    }
}
