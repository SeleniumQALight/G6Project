package apiTests;


import api.EndPointsPrivatBank;
import api.dto.privatBankDto.ExchangeDTO;
import api.dto.privatBankDto.MainExchangeDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class ApiPrivatBankTest {
    private final String DATE = "30.12.2022";
    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Test
    public void currencyRate(){
        MainExchangeDTO responseDTO = given()
                .contentType(ContentType.JSON)
                .queryParam("data", DATE)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(MainExchangeDTO.class);

        List<ExchangeDTO> listOfRates = List.of(
                ExchangeDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("AZN").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("BYN").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("CAD").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("CHF").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("CNY").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("CZK").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("DKK").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("EUR").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("GBP").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("GEL").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("HUF").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("ILS").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("JPY").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("KZT").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("MDL").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("NOK").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("PLN").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("SEK").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("SGD").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("TMT").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("TRY").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("UAH").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("USD").build(),
                ExchangeDTO.builder().baseCurrency("UAH").currency("UZS").build()
        );

        MainExchangeDTO expectedResult =
                MainExchangeDTO.builder()
                        .date("30.12.2022")
                        .bank("PB").baseCurrency(980)
                        .baseCurrencyLit("UAH")
                        .exchangeRate(listOfRates)
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseDTO)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.purchaseRate", "exchangeRate.saleRate")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();
    }

    @Test
    public void getCurrencySchema(){
        Response expectedResponse =
        given()
                .contentType(ContentType.JSON)
                .queryParam("date", DATE)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        List<Double> saleRateNB = expectedResponse.jsonPath().getList("exchangeRate.saleRateNB", Double.class);
        List<Double> purchaseRateNB = expectedResponse.jsonPath().getList("exchangeRate.purchaseRateNB", Double.class);
        List<Double> saleRate = expectedResponse.jsonPath().getList("exchangeRate.saleRate", Double.class);
        List<Double> purchaseRate = expectedResponse.jsonPath().getList("exchangeRate.purchaseRate", Double.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < saleRateNB.size(); i++) {
            softAssertions.assertThat(saleRateNB.get(i)).as("Number " + i).isGreaterThan(0);
        }

        for (int i = 0; i < saleRateNB.size(); i++) {
            softAssertions.assertThat(purchaseRateNB.get(i)).as("Number " + i).isGreaterThan(0);
        }

        for (int i = 0; i < purchaseRate.size(); i++) {
            if (purchaseRate.get(i) != null) {
                softAssertions.assertThat(purchaseRate.get(i)).as("Number " + i).isGreaterThan(0);
            }
        }

        for (int i = 0; i < saleRate.size(); i++) {
            if (saleRate.get(i) != null) {
                softAssertions.assertThat(saleRate.get(i)).as("Number " + i).isGreaterThan(0);
            }
        }

        softAssertions.assertAll();
    }
}
