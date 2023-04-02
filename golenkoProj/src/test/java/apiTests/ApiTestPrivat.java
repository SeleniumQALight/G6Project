package apiTests;

import api.EndPointsPrivat;
import api.ExchangeRateDTO;
import api.GeneralExchangeDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestPrivat {
    private final String SELECTED_DATE = "22.03.2022";
    private final Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCurrencyRate() {
        GeneralExchangeDTO responseAsDto = given()
                .contentType(ContentType.JSON)
                .queryParam("date", SELECTED_DATE)
                .log().all()
                .when()
                .get(EndPointsPrivat.CURRENCY_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(GeneralExchangeDTO.class);


        List<ExchangeRateDTO> listOfAllRates = List.of(
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
                ExchangeRateDTO.builder().baseCurrency("UAH").currency("UZS").build()
        );

        GeneralExchangeDTO expectedResult =
                GeneralExchangeDTO.builder()
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
                .queryParam("date", SELECTED_DATE)
                .log().all()
                .when()
                .get(EndPointsPrivat.CURRENCY_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("privatCurrency.json"));
    }

    @Test
    public void validateCurrencyNBValue(){
        Response actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", SELECTED_DATE)
                        .log().all()
                        .when()
                        .get(EndPointsPrivat.CURRENCY_RATES)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        List<String> saleRateNB = actualResponse.jsonPath().getList("exchangeRate.saleRateNB", String.class);
        List<String> purchaseRateNB = actualResponse.jsonPath().getList("exchangeRate.purchaseRateNB", String.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < saleRateNB.size(); i++) {
            softAssertions.assertThat(saleRateNB.get(i)).as("Item number " + i).isGreaterThan("0");
        }

        for (int i = 0; i < saleRateNB.size(); i++) {
            softAssertions.assertThat(purchaseRateNB.get(i)).as("Item number " + i).isGreaterThan("0");
        }


        softAssertions.assertAll();

    }


}
