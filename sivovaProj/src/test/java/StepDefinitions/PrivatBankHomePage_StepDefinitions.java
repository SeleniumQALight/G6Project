package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.junit.Assert;
import pages.PrivatBankHomePage;

public class PrivatBankHomePage_StepDefinitions {
    PrivatBankHomePage privatBankHomePage = new PrivatBankHomePage(DriverHelper.getWebdriver());

    @When("^User goes to 'PrivatBank Home page'$")
    public void userGoesToPrivatBankHomePage() {
        privatBankHomePage.openPrivatBankHomePage();
    }

    @When("^Get '(.*)' currency exchange rate from UI$")
    public void getCurrencyExchangeRateFromUI(String currency) {
        privatBankHomePage.getCurrencyExchangeFromUI(currency);
    }

    @Then("^compare '(.*)' exchange rate from UI and API$")
    public void compareCurrencyExchangeRateFromUIAndAPI(String currency) {
        Double exchangeRateUIBuy = Double.parseDouble(TestData.ui_buy);
        Assert.assertEquals("Currencies for " + currency + " do not match", TestData.api_buy, exchangeRateUIBuy);
        Double  exchangeRateUISell = Double.parseDouble(TestData.ui_sell);
        Assert.assertEquals("Currencies for " + currency + " do not match", TestData.api_sell, exchangeRateUISell);

    }
}
