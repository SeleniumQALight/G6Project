package pages;

import api.EndPointsPrivatBank;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PrivatHomePage {
    public WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    String url = "";
    private String buyRate = ".//*[@id='%s_buy']";
    private String sellRate = ".//*[@id='%s_sell']";
    public PrivatHomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    public PrivatHomePage openHomePage(){
        try {
            webDriver.get(EndPointsPrivatBank.baseURLPrivatUI);
            logger.info("Privatbank Home Page was open");
        } catch (Exception e) {
            logger.error("Privatbank Home Page wasn't open" + e);
        }
        return this;
    }
    public double getBuyRateCurrency(String currency) {
        WebElement buyRateCurrency = webDriver.findElement(By.xpath(String.format(buyRate, currency)));
        logger.info("Buy rate was");
        return Double.parseDouble(buyRateCurrency.getText().trim());
    }
    public double getSellRateCurrency(String currency){
        WebElement sellRateCurrency = webDriver.findElement(By.xpath(String.format(sellRate, currency)));
        logger.info("Sell rate");
        return Double.parseDouble(sellRateCurrency.getText().trim());
    }
}
