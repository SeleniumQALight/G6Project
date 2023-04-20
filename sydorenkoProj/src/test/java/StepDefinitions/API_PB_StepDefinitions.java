package StepDefinitions;
import api.ApiHelperPB;
import api.dto.pb.CurrencyPBdto;
import cucumber.api.java.en.Given;
import libs.TestData;

import java.util.List;


public class API_PB_StepDefinitions {
    ApiHelperPB apiHelperPB = new ApiHelperPB();

    @Given("^Get '(.*)'/'(.*)' exchange from API$")
    public void getCurrencyBaseCurrencyExchangeFromAPI(String currency, String baseCurrency) {
        List<CurrencyPBdto> list = apiHelperPB.getCurrencyExchange();

        for (CurrencyPBdto element : list) {
            if (element.getCcy().equalsIgnoreCase(currency) && element.getBase_ccy().equalsIgnoreCase(baseCurrency)) {
                String keySell = currency + baseCurrency + TestData.SELL_KEY_API;
                String keyBuy = currency + baseCurrency + TestData.BUY_KEY_API;
                TestData.currencyPB.put(keySell, element.getSale());
                TestData.currencyPB.put(keyBuy, element.getBuy());
            }
        }
    }
}