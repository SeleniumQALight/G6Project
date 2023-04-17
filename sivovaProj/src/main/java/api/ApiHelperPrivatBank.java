package api;

import api.dto.responseDto.CurrencyPrivatDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;


import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {

    Logger logger = Logger.getLogger(getClass());
    public List<CurrencyPrivatDTO> getListOfCurrenciesById() {
            Response response =
                given()
                       .contentType(ContentType.JSON)
                        .queryParam("json", "")
                        .queryParam("exchange", "")
                        .queryParam("coursid", "5")
                        .log().all()
                        .when()
                        .get(EndPointsPrivat.CURRENCY_EXCHANGE_RATE_BASED_ON_ID)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        return response.jsonPath().getList("",CurrencyPrivatDTO.class);
    }

    public void getCurrencyExchangeRate(String currency) {
        List<CurrencyPrivatDTO> listOfCurrenciesAPI = getListOfCurrenciesById();

        for (CurrencyPrivatDTO currencyPrivatDTO: listOfCurrenciesAPI) {
            if (currencyPrivatDTO.getCcy().equalsIgnoreCase(currency)) {
                TestData.api_buy = currencyPrivatDTO.getBuy();
                TestData.api_sell = currencyPrivatDTO.getSale();
            }
        }
        if (TestData.api_buy == null || TestData.api_sell == null) {
            logger.info ("The exchange rate for currency " + currency +  "is not found");
            Assert.fail("The exchange rate for currency " + currency +  "is not found");
        }

    }

}
