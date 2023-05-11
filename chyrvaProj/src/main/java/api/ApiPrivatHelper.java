package api;

import api.dto.responseDto.ExchangeKursDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiPrivatHelper {
    Logger logger = Logger.getLogger(getClass());

    public List<ExchangeKursDto> getCurrencyExchangeRate() {
        Response exchangeRate =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .queryParam("json")
                        .queryParam("exchange")
                        .queryParam("coursid",5)
                        .get(EndPointsPrivatBankKurs.CURRENCY_KURS)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();
        return exchangeRate.jsonPath().getList("", ExchangeKursDto.class);


    }
}
