package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import pages.privat.HomePagePrivat;

public class HomePagePrivat_StepDefinitions {
    HomePagePrivat homePagePrivat = new HomePagePrivat(DriverHelper.getWebDriver());

    @When("^I go to the PrivatBank website$")
    public void iGoToThePrivatBankWebsite() {
        homePagePrivat.openHomePage();
    }

    @And("^I get the '(.*)'/'(.*)' exchange rate from the UI$")
    public void iGetTheCurrencyAndCurrencyExchangeRateFromTheUI(String currency, String baseCurrency) {
        TestData.privatCurrencies.put(currency + baseCurrency + TestData.BUY_UI_KEY, homePagePrivat.getCurrencyBuyRate(currency));
        TestData.privatCurrencies.put(currency + baseCurrency + TestData.SELL_UI_KEY, homePagePrivat.getCurrencySellRate(currency));
    }
}
