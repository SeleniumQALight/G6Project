package pages.privat;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePagePrivat extends BasePage {
    private final String currencyRate = ".//*[@id='%s_%s']";
    Logger logger = Logger.getLogger(getClass());

    public HomePagePrivat(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePagePrivat openHomePage() {
        try {
            webDriver.get(home_url_privat);
            logger.info("Home Page was opened");
        } catch (Exception e) {
            logger.error("Cannot open Home Page" + e);
            Assert.fail("Cannot open Home Page" + e);
        }
        return this;
    }

    public double getCurrencySellRate(String currency) {
        WebElement element = webDriver.findElement(By.xpath(String.format(currencyRate, currency, "sell")));
        return Double.parseDouble(element.getText().trim());
    }

    public double getCurrencyBuyRate(String currency) {
        WebElement element = webDriver.findElement(By.xpath(String.format(currencyRate, currency, "buy")));
        return Double.parseDouble(element.getText().trim());
    }
}
