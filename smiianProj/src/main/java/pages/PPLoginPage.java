package pages;

import libs.TestData;
//import org.junit.Assert;        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PPLoginPage extends PPParentPage {


    @FindBy(xpath = ".//td[contains(text(), '%s')]")
    private WebElement currency;


    public PPLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }



    public PPLoginPage openPrivatBankPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            loggerExam.info("PrivatPage is opened");
        } catch (Exception e) {
            loggerExam.error("Can't open Privat Page" + e);
        }
        return this;
    }


    public void getUiCurrencyBuyValueAndSave(String currencyText){            //  .//td[@id='USD_buy']
        WebElement element = webDriver.findElement(By.xpath(".//td[@id='" + currencyText + "_buy']"));
//        int currencyBuyValue = Integer.parseInt(element.getText());
        TestData.uiCurrencyValueBuy = Double.parseDouble(element.getText());
//        return Integer.parseInt(element.getText());
    }

    public void getUiCurrencySellValueAndSave(String currencyText){            //  .//td[@id='USD_sell']
        WebElement element = webDriver.findElement(By.xpath(".//td[@id='" + currencyText + "_sell']"));
        TestData.uiCurrencyValueSell = Double.parseDouble(element.getText());
    }

}
