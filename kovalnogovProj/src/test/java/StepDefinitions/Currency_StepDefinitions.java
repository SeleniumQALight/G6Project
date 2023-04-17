package StepDefinitions;

import api.privateBank.PrivatBankApiService;
import api.privateBank.PrivatExchnageRatesPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class Currency_StepDefinitions {

private PrivatExchnageRatesPage privatExchnageRatesPage = new PrivatExchnageRatesPage(DriverHelper.getWebDriver());
    @Given("^Open exchange-rates for currency (.*) via UI and get sale and buy vales$")
    public void open_exchange_rates_for_currency_USD_via_UI_and_get_sale_and_buy_vales(String currency) throws Throwable {
        privatExchnageRatesPage.openExchangeRatesPage().getCurrency(currency);
    }

    @When("^Get exchange rates via API for  currency (.*)$")
    public void get_exchange_rates_via_API_for_currency_USD(String currency) throws Throwable {
        PrivatBankApiService.getCurrencyRatesByApi(currency);
    }

    @Then("^Compare currency (.*)$")
    public void compare_currency_USD(String currency) throws Throwable {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(TestData.currencyFromUI.get(currency))
                .usingRecursiveComparison()
                .ignoringFields("base_ccy")
                .isEqualTo(TestData.currencyFromAPI.get(currency));

        softAssertions.assertAll();
    }
}
