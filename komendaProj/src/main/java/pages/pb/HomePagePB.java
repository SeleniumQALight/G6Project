package pages.pb;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePagePB extends BasePage {
    private final String currencyRatePB = ".//*[@id='%s_%s']";
    Logger logger = Logger.getLogger(getClass());

    public HomePagePB(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePagePB openHomePage() {
        try {
            webDriver.get(URL_PB);
            logger.info("Home page was opened ");
        } catch (Exception e) {
            logger.error("Home page did not open " + e);
            Assert.fail("Home page did not open " + e);
        }
        return this;
    }

    public double getCurrencySell(String currency) {
        WebElement element = webDriver.findElement(By.xpath(String.format(currencyRatePB, currency, "sell")));
        return Double.parseDouble(element.getText().trim());
    }

    public double getCurrencyBuy(String currency) {
        WebElement element = webDriver.findElement(By.xpath(String.format(currencyRatePB, currency, "buy")));
        return Double.parseDouble(element.getText().trim());
    }
}
