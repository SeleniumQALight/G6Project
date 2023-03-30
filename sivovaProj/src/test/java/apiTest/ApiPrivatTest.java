package apiTest;

import api.CurrencyDTO;
import api.EndPointsPrivat;
import api.ExchangeRateDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiPrivatTest {
    String date;

    @Test
    public void getAllCurrencyOnDateSchema() {
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
    public void getAllCurrencyOnDate() {
        CurrencyDTO responseCurrency = given().queryParam("date", "22.03.2022")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivat.CURRENCY_RATE_ON_DATE)
                .then()
                .statusCode(200)
                .extract().as(CurrencyDTO.class);


        List<ExchangeRateDTO> exchangeRateDTOList = List.of(
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


        CurrencyDTO expectedCurrency = CurrencyDTO.builder()
                .date("22.03.2022")
                .bank("PB")
                .baseCurrency(980)
                .baseCurrencyLit("UAH")
                .exchangeRate(exchangeRateDTOList)
                .build();


        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseCurrency)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.purchaseRate","exchangeRate.saleRate")
                .isEqualTo(expectedCurrency);

        softAssertions.assertAll();
    }

    @Test
    public void checkCurrencyFields() {
        CurrencyDTO responseCurrency = given().queryParam("date", "22.03.2022")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivat.CURRENCY_RATE_ON_DATE)
                .then()
                .statusCode(200)
                .extract().as(CurrencyDTO.class);

        List<ExchangeRateDTO> exchangeRateDTO = responseCurrency.getExchangeRate();

        SoftAssertions softAssertions = new SoftAssertions();

       for (int i = 0; i < exchangeRateDTO.size(); i++) {

           softAssertions.assertThat(exchangeRateDTO.get(i).getSaleRateNB()).isGreaterThan(0);
           softAssertions.assertThat(exchangeRateDTO.get(i).getPurchaseRateNB()).isGreaterThan(0);

           if (exchangeRateDTO.get(i).getPurchaseRate() != null) {
               softAssertions.assertThat(exchangeRateDTO.get(i).getPurchaseRate()).isGreaterThan(0);
           }

           if (exchangeRateDTO.get(i).getSaleRate() != null) {
               softAssertions.assertThat(exchangeRateDTO.get(i).getSaleRate()).isGreaterThan(0);
           }

       }
        softAssertions.assertAll();
    }

}
