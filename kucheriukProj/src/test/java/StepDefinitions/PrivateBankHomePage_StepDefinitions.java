package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.junit.Assert;
import pages.PrivateBankHomePage;

public class PrivateBankHomePage_StepDefinitions {
    PrivateBankHomePage privateBankHomePage = new PrivateBankHomePage(DriverHelper.getWebDriver());

    @When("^User opens PrivateBankHome page$")
    public void userOpensPrivateBankHomePage() {
        privateBankHomePage.openPrivateBankHomePage();
    }

    @And("^Get '(.*)' currency exchange rate from UI$")
    public void getCurrencyCurrencyExchangeRateFromUI(String currency) {
        privateBankHomePage.getCurrencyExchangeFromUI(currency);
    }

    @Then("^Compare '(.*)' exchange rate from UI and API$")
    public void compareCurrencyExchangeRateFromUIAndAPI(String currency) {
        Double exchangeRateUIBuy = Double.parseDouble(TestData.exchangeRatesUIBuy);
        Assert.assertEquals("Currencies for " + currency + " do not match", TestData.exchangeRatesApiBuy, exchangeRateUIBuy);
        Double exchangeRateUISell = Double.parseDouble(TestData.exchangeRateUISell);
        Assert.assertEquals("Currencies for " + currency + " do not match", TestData.exchangeRateApiSell, exchangeRateUISell);
    }
}
