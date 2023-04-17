package api.privateBank;

import api.PrivatApi;
import libs.TestData;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PrivatBankApiService {

    public static CurrencyDTO getCurrencyRatesByApi(String currency) {
        CurrencyDTO[] rates = given().log().all()
                .get(PrivatApi.EXCHANGE_RATES_API)
                .then()
                .log().all()
                .extract()
                .response()
                .as(CurrencyDTO[].class);

        CurrencyDTO dto = Arrays.asList(rates)
                .stream()
                .filter(t -> t.getCcy().equalsIgnoreCase(currency))
                .findFirst()
                .get();
        TestData.currencyFromAPI.put(currency,dto);
        return dto;
    }

}
