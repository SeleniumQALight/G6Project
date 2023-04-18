package API;

import API.DTO.responseDTO.PrivatDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivat {
    static final int id = 5;

    public static List<PrivatDTO> getCurrencyExchangeRates() {
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", id)
                .log().all()
                .when()
                .get(EndpointPrivatBank.EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response.jsonPath().getList("", PrivatDTO.class);
    }

}