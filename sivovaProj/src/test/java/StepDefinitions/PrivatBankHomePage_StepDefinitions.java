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

    @And("^Get '(.*)' currency exchange rate from UI$")
    public void getCurrencyExchangeRateFromUI(String currency) {
        privatBankHomePage.getCurrencyExchangeFromUI(currency);
    }

    @Then("^compare '(.*)' exchange rate from UI and API$")
    public void compareCurrencyExchangeRateFromUIAndAPI(String currency) {
        Double d = Double.parseDouble(TestData.UI_BUY);
        Assert.assertEquals("Currencies for " + currency + " do not match", TestData.API_BUY, d);
        d = Double.parseDouble(TestData.UI_SELL);
        Assert.assertEquals("Currencies for " + currency + " do not match", TestData.API_SELL, d);

    }
}
