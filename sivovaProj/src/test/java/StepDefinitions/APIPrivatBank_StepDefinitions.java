package StepDefinitions;

import api.ApiHelperPrivatBank;
import cucumber.api.java.en.Given;

public class APIPrivatBank_StepDefinitions {
    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Given("^Get '(.*)' currency exchange rate by '(.*)'$")
    public void getCurrencyCurrencyExchangeRateById(String currency, String id) {

    }
}
