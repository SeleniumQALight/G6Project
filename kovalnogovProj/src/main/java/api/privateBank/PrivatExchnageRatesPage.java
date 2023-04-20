package api.privateBank;

import api.PrivatApi;
import libs.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PrivatExchnageRatesPage {

    private WebDriver driver;
    private String BASEURL = "https://next.privat24.ua";
    public String EXCHANGE_RATES_UI = "/exchange-rates";

    public PrivatExchnageRatesPage(WebDriver driver) {
        this.driver = driver;
    }

    public PrivatExchnageRatesPage openExchangeRatesPage() {
        driver.get(BASEURL + EXCHANGE_RATES_UI);
        return this;
    }

    public CurrencyDTO getCurrency(String currency) {
        String buyValue = driver.findElement(By.xpath(".//div[contains(text(), '" + currency + "')]//parent::div//parent::div//following-sibling::div[starts-with(@class,'rate')][1]")).getText();
        String saleValue = driver.findElement(By.xpath(".//div[contains(text(), '" + currency + "')]//parent::div//parent::div//following-sibling::div[starts-with(@class,'rate')][2]")).getText();

        TestData.currencyFromUI.put(currency, CurrencyDTO.builder()
                .ccy(currency)
                .baseCcy("UAH")
                .buy(buyValue)
                .sale(saleValue)
                .build());

        return TestData.currencyFromUI.get(currency);
    }

    public void driverClose() {
        driver.quit();
    }
}
