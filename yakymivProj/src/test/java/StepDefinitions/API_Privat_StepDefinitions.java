package StepDefinitions;

import API.ApiHelperPrivat;
import API.DTO.responseDTO.PrivatDTO;
import cucumber.api.java.en.When;
import libs.TestData;

import java.util.List;

public class API_Privat_StepDefinitions {

    @When("^User get '(.*)' and '(.*)' from API$")
    public void user_get_BaseCurrency_and_Currency_from_API(String baseCurrency, String currency) {
        List<PrivatDTO> list = ApiHelperPrivat.getCurrencyExchangeRates();

        for (PrivatDTO element : list) {
            if (element.getCcy().equalsIgnoreCase(currency) && element.getBase_ccy().equalsIgnoreCase(baseCurrency)) {
                TestData.api_buy = element.getBuy();
                TestData.api_sell = element.getSale();
            }
        }

    }
}