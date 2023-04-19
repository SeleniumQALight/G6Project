package StepDefinitions;

import api.APIHelperPrivat;
import api.dto.CurrencyRatePrivatDTO;
import cucumber.api.java.en.Given;
import libs.TestData;

import java.util.List;

public class API_Privat_StepDefinitions {

    APIHelperPrivat apiHelperPrivat = new APIHelperPrivat();

    @Given("^I get the '(.*)'/'(.*)' exchange rates from the API$")
    public void iGetTheCurrencyExchangeRatesFromTheAPI(String currency, String baseCurrency) {
        List<CurrencyRatePrivatDTO> list = apiHelperPrivat.getCurrencyExchangeRates();

        for (CurrencyRatePrivatDTO element : list) {
            if (element.getCcy().equalsIgnoreCase(currency) && element.getBase_ccy().equalsIgnoreCase(baseCurrency)) {
                String keySell = currency + baseCurrency + TestData.SELL_API_KEY;
                String keyBuy = currency + baseCurrency + TestData.BUY_API_KEY;

                TestData.privatCurrencies.put(keySell, element.getSale());
                TestData.privatCurrencies.put(keyBuy, element.getBuy());
            }
        }
    }
}
