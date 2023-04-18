package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.junit.Assert;
import pages.PrivatHomePage;

public class PrivatHomePage_StepDefinitions {
    PrivatHomePage privatHomePage = new PrivatHomePage(DriverHelper.getWebDriver());

    @Given("^User opens PrivatHome page$")
    public void user_opens_PrivatHome_page() {
        privatHomePage.openHomePage();
    }

    @When("^User get buy and sell rate of '(.*)' from UI$")
    public void user_get_buy_and_sell_rate_of_Currency_from_UI(String currency) {
        TestData.ui_buy = privatHomePage.getCurrencyBuyRate(currency);
        TestData.ui_sell = privatHomePage.getCurrencySellRate(currency);
    }

    @Then("^User compare currency from UI and API '(.*)' and '(.*)'$")
    public void user_compare_currency_from_UI_and_API_baseCurrency_and_currency(String baseCurrency, String currency) {
        Assert.assertEquals("Rates Buy of " + currency + "for " + baseCurrency, TestData.ui_buy, TestData.api_buy);
        Assert.assertEquals("Rates Sell of: " + currency + "for " + baseCurrency, TestData.ui_sell, TestData.api_sell);

    }
}