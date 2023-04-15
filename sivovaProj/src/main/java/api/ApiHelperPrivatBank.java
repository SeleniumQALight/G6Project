package api;

import api.dto.responseDto.CurrencyPrivatDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import libs.TestData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {
    public List<CurrencyPrivatDTO> getListOfCurrenciesById() {
            Response response =
                given()
                       .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPointsPrivat.CURRENCY_EXCHANGE_RATE_BASED_ON_ID, 5)
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
                TestData.API_BUY = currencyPrivatDTO.getBuy();
                TestData.API_SELL = currencyPrivatDTO.getSale();

            }
        }
    }

}
