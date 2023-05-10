package api;

import api.dto.EndPointsPrivat;
import api.dto.PrivatDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivat {
    static final int ID = 5;
    public static List<PrivatDTO> getCurrencyFromAPI(){
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursi", ID)
                .log().all()
                .filter(new AllureRestAssured())
                .when()
                .get(EndPointsPrivat.EXCHENGE_RATE_URL)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response.jsonPath().getList("", PrivatDTO.class);

    }
}
