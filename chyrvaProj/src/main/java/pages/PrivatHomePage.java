package pages;

import api.EndPointsPrivatBankKurs;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PrivatHomePage {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    private String buyKurs = ".//*[@id='%s_buy']";
    private String sellKurs = ".//*[@id='%s_sell']";

    public  PrivatHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

        public PrivatHomePage openHomePagePrivat() {
            try {
                webDriver.get(EndPointsPrivatBankKurs.baseUrlPrivatUI);
                logger.info("Home page Private was opened");
            } catch (Exception e) {
                logger.error("Private Home Page doesn't open" + e);
            }
            return this;
        }
    public double getCurrencySellRate(String currency) {
        WebElement sellRateCurrency = webDriver.findElement(By.xpath(String.format(sellKurs, currency)));
        return Double.parseDouble(sellRateCurrency.getText().trim());
    }

    public double getCurrencyBuyRate (String currency){
        WebElement buyRateCurrency = webDriver.findElement(By.xpath(String.format(buyKurs, currency)));
        return Double.parseDouble(buyRateCurrency.getText().trim());
    }



}









