package StepDefinitions;

import api.privateBankAPI.PBApiHelper;
import api.privateBankAPI.PBExchangeRateDTO;
import cucumber.api.java.en.Given;
import libraries.PBTestData;


public class PB_Api_StepDefinitions {
    PBApiHelper pbApiHelper = new PBApiHelper();
    PBExchangeRateDTO[] currencyList = pbApiHelper.exchangeRateResponseDTO();

    @Given("^User obtains 'buy' and 'sale' rates for '(.*)' via API$")
    public void user_obtains_buy_and_sale_rates_for_EUR_via_API(String currency) throws Throwable {
        for (int i = 0; i < currencyList.length; i++) {
            if (currencyList[i].getCcy().equalsIgnoreCase(currency)){
                PBTestData.buyAPI = currencyList[i].getBuy();
                PBTestData.saleAPI = currencyList[i].getSale();
            }
        }
    }

}
