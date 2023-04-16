package pages;

import libs.TestData;
import org.junit.Assert;
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
            Assert.fail("Can't open Privat Page" + e);
        }
        return this;
    }

//    public WebElement getCurrencyValueONE (String CurrencyText){
//        return webDriver.findElement(By.xpath(String.format(CurrencyText, currency)));
//    }

    public String getCurrencyValue(String CurrencyText){            //  .//td[contains(text(), 'USD')]//..//td[3]
        WebElement element = webDriver.findElement(By.xpath(".//td[contains(text(), '" + CurrencyText + "')]//..//td[3]"));
        return element.getText();
    }





}
