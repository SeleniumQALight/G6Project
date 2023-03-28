package apiTest;

import api.CurrencyDTO;
import api.EndPointsPrivat;
import api.ExchangeRateDTO;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiPrivatTest {
    String date;

    @Test
    public void getAllCurrencyOnDateSchema(){
        given().queryParam("date", "22.03.2022")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivat.CURRENCY_RATE_ON_DATE)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("currency.json"));

    }

    @Test
    public void getAllCurrencyOnDate(){
        CurrencyDTO responseCurrency = given().queryParam("date", "22.03.2022")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivat.CURRENCY_RATE_ON_DATE)
                .then()
                .statusCode(200)
                .extract()
                .response().as(CurrencyDTO.class);


        List<ExchangeRateDTO> exchangeRateDTOList = List.of(
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("AZN").build()
        );

        CurrencyDTO expectedCurrency = {
            CurrencyDTO.builder().data("22.03.2022").bank("PB").baseCurrency(980).basCurrencyLit("UAH")
                    .exchange((ExchangeRateDTO) exchangeRateDTOList).build()
        };
    }
}
