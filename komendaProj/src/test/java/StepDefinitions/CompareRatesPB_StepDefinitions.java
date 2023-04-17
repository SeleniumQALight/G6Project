package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;

public class CompareRatesPB_StepDefinitions {

    @Then("^Compare exchange rates between UI and API '(.*)'/'(.*)'$")
    public void compareExchangeRatesForUIAndAPICurrencyBaseCurrency(String currency, String baseCurrency) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.currencyPB.get(currency + baseCurrency + TestData.BUY_KEY_API))
                .as(String.format("Buy rates currency %s/%s don't match ", currency, baseCurrency))
                .isEqualTo(TestData.currencyPB.get(currency + baseCurrency + TestData.BUY_KEY_UI));

        softAssertions.assertThat(TestData.currencyPB.get(currency + baseCurrency + TestData.SELL_KEY_API))
                .as(String.format("Sell rates currency %s/%s don't match ", currency, baseCurrency))
                .isEqualTo(TestData.currencyPB.get(currency + baseCurrency + TestData.SELL_KEY_UI));

        softAssertions.assertAll();


    }
}