package api;

import api.dto.responseDto.CurrencyPrivatDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {


    public List<CurrencyPrivatDTO> getListOfCurrenciesById(String Id) {
        Response response =
                given()
                       .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .post(EndPointsPrivat.CURRENCY_EXCHANGE_RATE_BASED_ON_ID, 5)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        return response.jsonPath().getList("", CurrencyPrivatDTO.class);
    }
}
