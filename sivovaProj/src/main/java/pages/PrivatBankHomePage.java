package pages;

import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PrivatBankHomePage {
    protected WebDriver webDriver;
    protected String base_url = "https://privatbank.ua/";
    Logger logger = Logger.getLogger(getClass());

    private String currencyUI = ".//div[@data-cource_type = 'posts_course' ]//td[text()='%s']";
    private String getCurrencyUI_Buy = ".//div[@data-cource_type = 'posts_course' ]//td[@id='%s_buy']";
    private String getCurrencyUI_Sell = ".//div[@data-cource_type = 'posts_course' ]//td[@id='%s_sell']";

//    @FindBy (xpath = ".//div[@data-cource_type = 'posts_course' ]//td[text()='%s']")
//    private WebElement currencyUI;

//    @FindBy (xpath = ".//div[@data-cource_type = 'posts_course' ]//td[@id='%s_buy']")
//    private WebElement getCurrencyUI_Buy;
//
//    @FindBy (xpath = ".//div[@data-cource_type = 'posts_course' ]//td[@id='%s_sell']")
//    private WebElement getCurrencyUI_Sell;

    public PrivatBankHomePage(WebDriver webdriver) {
            this.webDriver=webdriver;
            PageFactory.initElements(webDriver, this);

    }

    public PrivatBankHomePage openPrivatBankHomePage(){
        try{
            webDriver.get(base_url);
            logger.info("PrivatBank Home page was opened");
            logger.info(base_url);
            return this;

        } catch (Exception e) {
            logger.error("Can not open Home page" + e);
            Assert.fail("Can not open Home page" + e);
            return null;
        }
    }

    public void getCurrencyExchangeFromUI(String currency) {
        try {
            if (webDriver.findElement(By.xpath(String.format(currencyUI,currency))).isDisplayed()) {
                 TestData.UI_BUY = webDriver.findElement(By.xpath(String.format(getCurrencyUI_Buy, currency))).getText();
                 TestData.UI_SELL = webDriver.findElement(By.xpath(String.format(getCurrencyUI_Sell, currency))).getText();
            }

        } catch (Exception e) {
            logger.error("Can not work with element " + e);
            Assert.fail("Can not work with element " + e);
        }

    }

}
