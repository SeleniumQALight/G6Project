package apiTests;

import api.EndPoints;
import api.PBExchangeDTO;
import api.PBExchangeRatesDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPBTest {
    final String EXCHANGE_RATE_DATE = "01.12.2014";

    @Test
    public void getExchangeRateDate(){
        PBExchangeDTO responseAsDto = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("date",EXCHANGE_RATE_DATE)
                .log().all()
                .when()
                .get(EndPoints.PB_EXCHANGE_RATES_URL)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responsePB.json"))
                .extract()
                .as(PBExchangeDTO.class)
        ;

        PBExchangeRatesDTO[] expectedResultRatesDTO = {
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("CAD").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("CZK").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("DKK").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("HUF").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("ILS").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("JPY").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("LVL").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("LTL").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("NOK").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("SKK").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("SEK").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("CHF").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("GBP").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("USD").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("BYR").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("EUR").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("GEL").build(),
                PBExchangeRatesDTO.builder().baseCurrency("UAH").currency("PLZ").build()
        };

        PBExchangeDTO expectedResultDTO = PBExchangeDTO.builder()
                .date(EXCHANGE_RATE_DATE)
                .bank("PB")
                .baseCurrency(980)
                .baseCurrencyLit("UAH")
                .exchangeRate(expectedResultRatesDTO)
                .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB","exchangeRate.purchaseRateNB","exchangeRate.saleRate","exchangeRate.purchaseRate")
                .isEqualTo(expectedResultDTO);

        softAssertions.assertAll();
    }
}
