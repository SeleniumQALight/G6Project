package api;

import api.dto.EndPointsPrivatBank;
import api.dto.responseDto.PrivatbankDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ApiHelperPrivatbank {
    static final int ID = 5;

public static List<PrivatbankDTO> getCurrencyFromAPI(){

    Response response =
            given()
                    .contentType(ContentType.JSON)
                    .queryParam("json")
                    .queryParam("exchange")
                    .queryParam("coursid", ID)
                    .log().all()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(EndPointsPrivatBank.EXCHENGE_RATE_URL)
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();

    return response.jsonPath().getList("", PrivatbankDTO.class);
}

}
