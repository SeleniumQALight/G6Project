package StepDefinitionsPrivat;

import api.DTO.PrivatApiHelper;
import api.DTO.PrivatCurrencyExchangeDTO;
import cucumber.api.java.en.Given;
import libs.TestData;

import java.util.List;

public class API_StepDefinitionsPrivat {

    private PrivatApiHelper apiHelperPrivat = new PrivatApiHelper();

    @Given("^Get '(.*)'/'(.*)' exchange rates from Privat API$")
    public void getCurrencyBaseCurrencyExchangeRatesFromPrivatAPI(String currency, String baseCurrency) {
        List<PrivatCurrencyExchangeDTO> privatExchanges = apiHelperPrivat.getCurrencyExchange();

        for (PrivatCurrencyExchangeDTO element : privatExchanges) {
            if (element.getCcy().equalsIgnoreCase(currency) && element.getBase_ccy().equalsIgnoreCase(baseCurrency)) {
                String keySell = currency + baseCurrency + TestData.SELL_API;
                String keyBuy = currency + baseCurrency + TestData.BUY_API;

                TestData.privatCurrency.put(keySell, element.getSale());
                TestData.privatCurrency.put(keyBuy, element.getBuy());

        }


}}


}



