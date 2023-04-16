package apiTest;

import api.EndPointsProj;
import api.dto.responseDto.CurrencyRespPrivatDTO;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class ApiProject {

    @Test
    public void getCurrencyValueFromPrivat(String currencyName) {

        CurrencyRespPrivatDTO[] currencyRestPrivatDTO =
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParams("coursid",5)
                .queryParams("exchange", "")
             .when()
                .get(EndPointsProj.GET_USER)
             .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyRespPrivatDTO[].class);

//        String ccyEur = currencyRestPrivatDTO[0].getCcy();

        for (int i = 0; i < currencyRestPrivatDTO.length; i++) {
            if (currencyRestPrivatDTO[i].getCcy().equals(currencyName)) {
// якщо equals, то зберегти в testData
            }
        }
    }
}
