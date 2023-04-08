package apiTest;

import api.*;
import api.dto.responseDto.PrivateHwOneDTO;
import api.dto.responseDto.ExchangeRateDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PrivatApiTests {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void homeWorkOne() {
        PrivateHwOneDTO responsePrivateHwOneDTO =
                given()
                        .contentType(ContentType.JSON)
                        .queryParams("date","22.03.2022")
                        .log().all()
                     .when()
                        .get(EndPointsPrivatBank.PRIVATE_API_22_03)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(PrivateHwOneDTO.class);



        ExchangeRateDTO[] listExchange = {
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("AZN").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("BYN").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CAD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CHF").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CNY").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CZK").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("DKK").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("EUR").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("GBP").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("GEL").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("HUF").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("ILS").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("JPY").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("KZT").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("MDL").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("NOK").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("PLN").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("SEK").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("SGD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("TMT").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("TRY").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("UAH").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("USD").build(),
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("UZS").build(),
        };

        PrivateHwOneDTO expectedResultHwOne =
                PrivateHwOneDTO.builder().date("22.03.2022").bank("PB").baseCurrency(980).baseCurrencyLit("UAH")
                        .exchangeRate(listExchange).build();


        SoftAssertions softAssertionsHwOne = new SoftAssertions();

        softAssertionsHwOne.assertThat(responsePrivateHwOneDTO)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB"
                        , "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResultHwOne);

        softAssertionsHwOne.assertAll();
    }

    @Test
    public void getAllPostsByUsersSchema() {
        given()
                .contentType(ContentType.JSON)
                .queryParams("date", "22.03.2022")
                .log().all()
                .when()
                .get(EndPointsPrivatBank.PRIVATE_API_22_03)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("respPrivatBank.json"));
    }
}
