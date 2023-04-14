package api;

import api.dto.pb.CurrencyPBdto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperPB {
    public List<CurrencyPBdto> getCurrencyExchange() {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.CURRENCY_EXCHANGE)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();
        return response.jsonPath().getList("", CurrencyPBdto.class);
    }
}
