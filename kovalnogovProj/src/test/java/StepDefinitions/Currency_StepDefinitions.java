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


public class Currency_StepDefinitions {

private PrivatExchnageRatesPage privatExchnageRatesPage = new PrivatExchnageRatesPage(DriverHelper.getWebDriver());

    @Given("^Open exchange-rates for currency '(.*)' via UI and get sale and buy vales$")
    public void openExchangeRatesForCurrencyCurrencyViaUIAndGetSaleAndBuyVales(String currency) {
        privatExchnageRatesPage.openExchangeRatesPage().getCurrency(currency);
    }


    @When("^Get exchange rates via API for  currency '(.*)'$")
    public void getExchangeRatesViaAPIForCurrencyCurrency(String currency) {
        PrivatBankApiService.getCurrencyRatesByApi(currency);
    }

    @Then("^Compare currency '(.*)'$")
    public void compareCurrencyCurrency(String currency) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.currencyFromUI.get(currency))
                .usingRecursiveComparison()
                .ignoringFields("base_ccy")
                .isEqualTo(TestData.currencyFromAPI.get(currency));

        softAssertions.assertAll();
    }
}
