package apiTests;

import API.EndpointPrivatBank;
import API.PrivatExchange;
import API.PrivatExchangeRateDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PrivateApiTest {
    private final String DATE = "22.03.2022";

    @Test
    public void getCurrencyRate() {
        PrivatExchange responseAsDto = given()
                .contentType(ContentType.JSON)
                .queryParam("date", DATE)
                .log().all()
                .when()
                .get(EndpointPrivatBank.exchngeRate)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatExchange.class);


        List<PrivatExchangeRateDTO> listOfAllRates = List.of(
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("AZN").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("BYN").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("CAD").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("CHF").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("CNY").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("CZK").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("DKK").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("EUR").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("GBP").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("GEL").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("HUF").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("ILS").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("JPY").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("KZT").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("MDL").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("NOK").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("PLN").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("SEK").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("SGD").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("TMT").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("TRY").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("UAH").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("USD").build(),
                PrivatExchangeRateDTO.builder().baseCurrency("UAH").currency("UZS").build()
        );

        PrivatExchange expectedResult =
                PrivatExchange.builder()
                        .date("22.03.2022")
                        .bank("PB").baseCurrency(980)
                        .baseCurrencyLit("UAH")
                        .exchangeRate(listOfAllRates)
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.purchaseRate", "exchangeRate.saleRate")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();
    }

    @Test
    public void getAllCurrencySchema(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("date", DATE)
                .log().all()
                .when()
                .get(EndpointPrivatBank.exchngeRate)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responsePrivat.json"));
    }


}