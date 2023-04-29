package api.privateBankAPI;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class PBApiHelper {

    public PBExchangeRateDTO[] exchangeRateResponseDTO(){
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", 5)
                .when()
                .get(PBEndPoints.pbBaseUrl)
                .then()
                .statusCode(200)
                .log().all().extract().response().getBody().as(PBExchangeRateDTO[].class);
    }
}
