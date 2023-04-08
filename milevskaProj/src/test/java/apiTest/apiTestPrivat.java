package apiTest;

import api.EndpointPrivatBank;
import api.PrivatExchange;
import api.PrivatExchangeRateDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class apiTestPrivat {
String responseData = "22.03.2022";
    @Test
    public void getExchangeTest() {
        PrivatExchange responsePrivat =
                given().contentType(ContentType.JSON)
                        .queryParam("json")
                        .queryParam("date", responseData)
                        .log().all()
                        .when()
                        .get(EndpointPrivatBank.exchngeRate)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(PrivatExchange.class);

        PrivatExchangeRateDTO[] expectedCurrency = {
                PrivatExchangeRateDTO.builder().currency("AUD").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("AZN").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("BYN").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("CAD").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("CHF").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("CNY").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("CZK").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("DKK").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("EUR").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("GBL").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("GEL").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("ILS").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("KZT").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("NOK").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("PLN").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("SEK").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("SGT").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("TMT").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("UAH").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("USD").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("UZS").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("HUF").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("JPY").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("MDL").baseCurrency("UAH").build(),
                PrivatExchangeRateDTO.builder().currency("TRY").baseCurrency("UAH").build()
        };

        PrivatExchange expectedResult = PrivatExchange.builder().date("22.03.2022").bank("PB")
                .baseCurrency(980).baseCurrencyLit("UAH")
                .exchangeRate(expectedCurrency).build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(responsePrivat)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResult);
        softAssertions.assertAll();
    }

    @Test
    public void shemaPrivatBank(){
        given().contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("date", responseData)
                .log().all()
                .when()
                .get(EndpointPrivatBank.exchngeRate)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("responsePrivat.json"));
    }
}
