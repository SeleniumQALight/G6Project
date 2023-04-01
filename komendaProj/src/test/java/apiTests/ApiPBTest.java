package apiTests;

import api.EndPoints;
import api.ExchangeRatePBuahDTO;
import api.ExchangeRatesPBDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPBTest {
    final String DATA = "22.03.2022";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRatesPBTest() {
        ExchangeRatesPBDTO responseAsDTO =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.EXCHANGE_RATES_PB, DATA)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response()
                        .as(ExchangeRatesPBDTO.class);

        logger.info("Name Bank = " + responseAsDTO.getBank());
        logger.info("Number of line = " + responseAsDTO.getExchangeRate().length);
        logger.info("Currency = " + responseAsDTO.getExchangeRate()[0].getCurrency());


        ExchangeRatesPBDTO expectedResult = ExchangeRatesPBDTO.builder()
                .date("22.03.2022").bank("PB").baseCurrency(980).baseCurrencyLit("UAH")
                .exchangeRate(new ExchangeRatePBuahDTO[]
                        {
                                ExchangeRatePBuahDTO.builder().currency("AUD").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("AZN").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("BYN").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("CAD").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("CHF").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("CNY").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("CZK").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("DKK").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("EUR").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("GBP").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("GEL").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("HUF").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("ILS").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("JPY").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("KZT").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("MDL").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("NOK").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("PLN").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("SEK").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("SGD").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("TMT").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("TRY").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("UAH").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("USD").baseCurrency("UAH").build(),
                                ExchangeRatePBuahDTO.builder().currency("UZS").baseCurrency("UAH").build()
                        }).build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseAsDTO)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();
    }

    @Test
    public void getExchangeRatesPBSchemaTest() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.EXCHANGE_RATES_PB, DATA)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("data-pb-response.json"));
    }

    @Test
    public void currencyRateValueCheckPB() {
        Response actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", DATA)
                        .log().all()
                        .when()
                        .get(EndPoints.EXCHANGE_RATES_PB1)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();

        List<String> saleRateNBlist =
                actualResponse.jsonPath().getList("exchangeRate.saleRateNB", String.class);
        List<String> purchaseRateNBList =
                actualResponse.jsonPath().getList("exchangeRate.purchaseRateNB", String.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < saleRateNBlist.size(); i++) {
            softAssertions.assertThat(purchaseRateNBList.get(i)).as("Number " + i).isGreaterThan("0");
        }
        for (int i = 0; i < purchaseRateNBList.size(); i++) {
            softAssertions.assertThat(purchaseRateNBList.get(i)).as("Number " + i).isGreaterThan("0");
        }
        softAssertions.assertAll();
    }
}