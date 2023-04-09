package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.TestData;
import org.junit.Assert;

public class CompareRates_StepDefinitions {

    @Then("^the API exchange rate should match the UI exchange rate for '(.*)'/'(.*)'$")
    public void theAPIExchangeRateShouldMatchTheUIExchangeRate(String currency, String baseCurrency) {
        Assert.assertEquals(String.format("Buy rates for currency %s/%s don't match", currency, baseCurrency),
                TestData.privatCurrencies.get(currency + baseCurrency + TestData.BUY_UI_KEY),
                TestData.privatCurrencies.get(currency + baseCurrency + TestData.BUY_API_KEY));

        Assert.assertEquals(String.format("Sell rates for currency %s/%s don't match", currency, baseCurrency),
                TestData.privatCurrencies.get(currency + baseCurrency + TestData.SELL_UI_KEY),
                TestData.privatCurrencies.get(currency + baseCurrency + TestData.SELL_API_KEY));
    }
}
