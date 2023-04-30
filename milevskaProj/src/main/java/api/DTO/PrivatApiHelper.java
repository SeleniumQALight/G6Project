package api.DTO;

import api.EndpointPrivatBank;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;


public class PrivatApiHelper {
    public List<PrivatCurrencyExchangeDTO> getCurrencyExchange(){
        Response exchange =
             given()
                     .contentType(ContentType.JSON)
                     .queryParam("json", "")
                     .queryParam("exchange", "")
                     .queryParam("coursid", "5")
                     .log().all()
                     .when()
                     .get(EndpointPrivatBank.exchangeAPI)
                     .then()
                     .statusCode(200)
                     .log().all()
                     .extract().response();
        return exchange.jsonPath().getList("", PrivatCurrencyExchangeDTO.class);
    }
}
