package apiTests;

import api.PrivatApi;
import api.privateBank.ExchangeRate;
import api.privateBank.ExchangeRatesInfo;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestPrivateBank {
    private String currentDate = "27.03.2022";

    @Test
    public void getExchangeRates() {
        ExchangeRatesInfo rates = given().contentType(ContentType.JSON)
                .queryParam("date", currentDate)
                .log().all()
                .when()
                .get(PrivatApi.EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("exchangeRates.json"))
                .extract()
                .as(ExchangeRatesInfo.class);


        List<ExchangeRate> listAllRates = List.of(
                ExchangeRate.builder().baseCurrency("UAH").currency("AUD").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("AZN").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("BYN").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("CAD").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("CHF").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("CNY").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("CZK").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("DKK").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("EUR").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("GBP").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("GEL").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("HUF").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("ILS").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("JPY").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("KZT").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("MDL").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("NOK").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("PLN").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("SEK").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("SGD").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("TMT").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("TRY").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("UAH").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("USD").build(),
                ExchangeRate.builder().baseCurrency("UAH").currency("UZS").build()
        );

        ExchangeRatesInfo expectedRates = ExchangeRatesInfo.builder()
                .date(currentDate)
                .bank("PB")
                .baseCurrency(980)
                .baseCurrencyLit("UAH")
                .exchangeRate(listAllRates)
                .build();
        Assert.assertTrue("Lists sizes are different", rates.getExchangeRate().size() == listAllRates.size());
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(rates)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedRates);

        softAssertions.assertAll();
    }
}
