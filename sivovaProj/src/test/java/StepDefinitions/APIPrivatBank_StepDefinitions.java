package StepDefinitions;

import api.ApiHelperPrivatBank;
import api.dto.responseDto.CurrencyPrivatDTO;
import cucumber.api.java.en.Given;
import libs.TestData;

import java.util.List;

public class APIPrivatBank_StepDefinitions {
    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Given("^Get '(.*)' currency exchange rate$")
    public void getCurrencyExchangeRateAPI(String currency) {
        apiHelperPrivatBank.getCurrencyExchangeRate(currency);
    }
}
