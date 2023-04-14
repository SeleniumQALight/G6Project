package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.TestData;
import org.junit.Assert;


public class CompareRatesPB_StepDefinitions {

    @Then("^Compare exchange rates between UI and API '(.*)'/'(.*)'$")
    public void compareExchangeRatesForUIAndAPICurrencyBaseCurrency(String currency, String baseCurrency) {
        Assert.assertEquals(String.format("Buy rates currency %s/%s don't match ", currency, baseCurrency),
                TestData.currencyPB.get(currency + baseCurrency + TestData.BUY_KEY_UI),
                TestData.currencyPB.get(currency + baseCurrency + TestData.BUY_KEY_API));

        Assert.assertEquals(String.format("Sell rates currency %s/%s don't match ", currency, baseCurrency),
                TestData.currencyPB.get(currency + baseCurrency + TestData.SELL_KEY_UI),
                TestData.currencyPB.get(currency + baseCurrency + TestData.SELL_KEY_API));
    }
}
