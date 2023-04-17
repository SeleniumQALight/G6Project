package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;

public class CompareRates_StepDefinitions {

    @Then("^the API exchange rate should match the UI exchange rate for '(.*)'/'(.*)'$")
    public void theAPIExchangeRateShouldMatchTheUIExchangeRate(String currency, String baseCurrency) {

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.privatCurrencies.get(currency + baseCurrency + TestData.BUY_API_KEY))
                .as(String.format("Buy rates for currency %s/%s don't match", currency, baseCurrency))
                .isEqualTo(TestData.privatCurrencies.get(currency + baseCurrency + TestData.BUY_UI_KEY));

        softAssertions.assertThat(TestData.privatCurrencies.get(currency + baseCurrency + TestData.SELL_API_KEY))
                .as(String.format("Sell rates for currency %s/%s don't match", currency, baseCurrency))
                .isEqualTo(TestData.privatCurrencies.get(currency + baseCurrency + TestData.SELL_UI_KEY));

        softAssertions.assertAll();
    }
}
