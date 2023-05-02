package StepDefinitions;

import api.ApiHelperPrivateBank;
import cucumber.api.java.en.Given;

public class PrivateBankApi_StepDefinitions {
    ApiHelperPrivateBank apiHelperPrivateBank = new ApiHelperPrivateBank();

    @Given("^Get '(.*)' currency exchange rate$")
    public void getCurrencyExchangeRate(String currency) {
        apiHelperPrivateBank.getCurrencyExchangeRate(currency);
    }
}
