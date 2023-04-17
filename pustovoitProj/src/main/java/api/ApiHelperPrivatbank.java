package api;

import api.dto.EndPointsPrivatBank;
import api.dto.responseDto.PrivatbankDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ApiHelperPrivatbank {

public static List<PrivatbankDTO> getCurrencyFromAPI(){

    Response response =
            given()
                    .contentType(ContentType.JSON)
                    .log().all()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(EndPointsPrivatBank.exchengeRateURL)
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();

    return response.jsonPath().getList("", PrivatbankDTO.class);
}

}
