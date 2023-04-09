package api;

import api.dto.CurrencyRatePrivatDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIHelperPrivat {

    public List<CurrencyRatePrivatDTO> getCurrencyExchangeRates() {
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivat.EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response.jsonPath().getList("", CurrencyRatePrivatDTO.class);
    }

}
