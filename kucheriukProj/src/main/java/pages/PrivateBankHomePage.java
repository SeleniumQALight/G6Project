package pages;

import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PrivateBankHomePage {

    protected WebDriver webDriver;
    protected String base_url = "https://privatbank.ua/";

    Logger logger = Logger.getLogger(getClass());

    private String currencyExchangeRateUI = ".//div[@data-cource_type = 'posts_course' ]//td[text()='%s']";
    private String currencyExchangeRatUIBuy = ".//div[@data-cource_type = 'posts_course' ]//td[@id='%s_buy']";
    private String currencyExchangeRatUISell = ".//div[@data-cource_type = 'posts_course' ]//td[@id='%s_sell']";

    public PrivateBankHomePage(WebDriver webdriver) {
        this.webDriver=webdriver;
        PageFactory.initElements(webDriver, this);

    }

    public PrivateBankHomePage openPrivateBankHomePage() {
        try{
            webDriver.get(base_url);
            logger.info("PrivateBank Home page was opened");
            logger.info(base_url);
            return this;

        } catch (Exception e) {
            logger.error("Can not open Home page" + e);
            Assert.fail("Can not open Home page" + e);
            return null;
        }
    }

    public WebElement getByCurrencyElement(String xpath, String currency) {
        return webDriver.findElement(By.xpath(String.format(xpath,currency)));

    }
    public void getCurrencyExchangeFromUI(String currency) {
        try {
            if ((getByCurrencyElement(currencyExchangeRateUI, currency)).isDisplayed()) {
                TestData.exchangeRatesUIBuy = getByCurrencyElement(currencyExchangeRatUIBuy, currency).getText();
                TestData.exchangeRateUISell = getByCurrencyElement(currencyExchangeRatUISell, currency).getText();
            }

        } catch (Exception e) {
            logger.error("Can not work with element " + e);
            Assert.fail("Can not work with element " + e);
        }
    }
}
