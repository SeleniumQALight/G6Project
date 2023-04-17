package apiTest;

import api.EndPointsProj;
import api.dto.responseDto.CurrencyRespPrivatDTO;
import io.restassured.http.ContentType;
import libs.TestData;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class ApiProject {

    @Test
    public void getFromPrivatCurrencyValueAndSave(String currencyName) {

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
                TestData.apiCurrencyValueBuy = Integer.parseInt(currencyRestPrivatDTO[i].getBuy());
                TestData.apiCurrencyValueSell = Integer.parseInt(currencyRestPrivatDTO[i].getSale());
//                TestData.apiCurValueBuy = currencyRestPrivatDTO[i].getBuy();
            }
        }
    }
}
