package StepDefinitionsPrivat;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import pagesPrivat.MainPagePrivat;

public class StepDefinitionsPrivat {

    MainPagePrivat mainPagePrivat = new MainPagePrivat(DriverHelper.getWebDriver());
    
    @When("User open Privat 'Main Page'")
    public void userOpenPrivatMainPage() {
        mainPagePrivat.openMainPage();
    }

    @And("^Get '(.*)'/'(.*)' exchange rates from Privat 'Main Page'$")
    public void getCurrencyBaseCurrencyExchangeRatesFromPrivatMainPage(String currency, String baseCurrency) {
        TestData.privatCurrency.put(currency + baseCurrency + TestData.BUY_UI, mainPagePrivat.getCurrencyBuyRate(currency));
        TestData.privatCurrency.put(currency + baseCurrency + TestData.SELL_UI, mainPagePrivat.getCurrencySellRate(currency));
    }

    @Then("^Check matching exchange rates from Api and Main Page$")
    public void checkMatchingExchangeRatesFromApiAndMainPage(String currency, String baseCurrency) {

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.privatCurrency.get(currency + baseCurrency + TestData.BUY_API))
                .as(String.format("Buy rates for currency %s/%s don't match", currency, baseCurrency))
                .isEqualTo(TestData.privatCurrency.get(currency + baseCurrency + TestData.BUY_UI));

        softAssertions.assertThat(TestData.privatCurrency.get(currency + baseCurrency + TestData.SELL_API))
                .as(String.format("Sell rates for currency %s/%s don't match", currency, baseCurrency))
                .isEqualTo(TestData.privatCurrency.get(currency + baseCurrency + TestData.SELL_UI));

        softAssertions.assertAll();
    }
}
    



