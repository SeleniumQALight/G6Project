package apiTests;

import api.dto.responseDTO.BPExchangeRateDTO;
import api.Endpoinds;
import api.dto.responseDTO.PBExchangeDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PrivatBankTest {
    final String EXCHANGE_RATE_DATE = "01.12.2014";

    @Test
    public void getExchangeRateByDate() {
        PBExchangeDTO responseAsDTO =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("json")
                        .queryParam("date",EXCHANGE_RATE_DATE)
                        .log().all()
                        .when()
                        .get(Endpoinds.PB_EXCHANGE_RATE_URL)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .assertThat().body(matchesJsonSchemaInClasspath("responsePBExchange.json"))
                        .extract()
                        .as(PBExchangeDTO.class)
                ;

        BPExchangeRateDTO[] expectedResultExchangeRateDTO = {
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("CAD").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("CZK").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("DKK").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("HUF").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("ILS").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("JPY").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("LVL").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("LTL").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("NOK").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("SKK").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("SEK").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("CHF").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("GBP").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("USD").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("BYR").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("EUR").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("GEL").build(),
                BPExchangeRateDTO.builder().baseCurrency("UAH").currency("PLZ").build()
        };

        PBExchangeDTO expectedResultExchangeDTO = new PBExchangeDTO(
                "01.12.2014"
                , "PB"
                , 980
                , "UAH"
                , expectedResultExchangeRateDTO
        );

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseAsDTO)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResultExchangeDTO);

        softAssertions.assertAll();
    }
}
