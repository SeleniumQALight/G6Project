package apiTest;

import api.EndPoints;
import api.dto.responseDto.ExchangeRateDTO;
import api.dto.responseDto.PrivateBankDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PBAPITest {
    Logger logger = Logger.getLogger(getClass());
    String responseDate = "27.03.2022";

    @Test
    public void getCurrencyData() {
        PrivateBankDTO responsePrivateBankDTO =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("json")
                        .queryParam("date", responseDate)
                        .log().all()
                        .when()
                        .get(EndPoints.EXCHANGE_RATES_API_PB)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .assertThat().body(matchesJsonSchemaInClasspath("exchange_rates.json"))
                        .extract()
                        .response()
                        .as(PrivateBankDTO.class);

        PrivateBankDTO expectedPrivateBankDTO = PrivateBankDTO.builder()
                .date(responseDate).bank("PB").baseCurrency(980).baseCurrencyLit("UAH")
                .exchangeRate(new ExchangeRateDTO[]
                        {ExchangeRateDTO.builder()
                                .baseCurrency("UAH").currency("AUD").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("AZN").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("BYN").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("CAD").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("CHF").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("CNY").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("CZK").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("DKK").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("EUR").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("GBP").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("GEL").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("HUF").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("ILS").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("JPY").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("KZT").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("MDL").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("NOK").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("PLN").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("SEK").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("SGD").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("TMT").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("TRY").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("UAH").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("USD").build(),
                                ExchangeRateDTO.builder()
                                        .baseCurrency("UAH").currency("UZS").build()}

                )
                .build();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(responsePrivateBankDTO)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedPrivateBankDTO);

        softAssertions.assertAll();
    }

    @Test
    public void currencyRateNBValueCheck() {
        Response actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("json")
                        .queryParam("date", responseDate)
                        .log().all()
                        .when()
                        .get(EndPoints.EXCHANGE_RATES_API_PB)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();

        List<String> saleRateNBList = actualResponse.jsonPath().getList("exchangeRate.saleRateNB", String.class);
        List<String> purchaseRateNBList = actualResponse.jsonPath().getList("exchangeRate.purchaseRateNB", String.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < saleRateNBList.size(); i++) {
            softAssertions.assertThat(saleRateNBList.get(i)).as("Item number " + i).isGreaterThan("0");
        }
        for (int i = 0; i < purchaseRateNBList.size(); i++) {
            softAssertions.assertThat(purchaseRateNBList.get(i)).as("Item number " + i).isGreaterThan("0");
        }

        softAssertions.assertAll();
    }

    @Test
    public void currencyRateValueCheck() {
        Response actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("json")
                        .queryParam("date", responseDate)
                        .log().all()
                        .when()
                        .get(EndPoints.EXCHANGE_RATES_API_PB)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();
        List<Map> saleRate = actualResponse.jsonPath().getList("exchangeRate", Map.class);
        List<Map> purchaseRate = actualResponse.jsonPath().getList("exchangeRate", Map.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < saleRate.size(); i++) {
            Map<String, Float> rate = saleRate.get(i);
            if(rate.containsKey("saleRate")){
                Float saleRateValue = rate.get("saleRate");
                softAssertions.assertThat(saleRateValue).as("Item # "+i).isGreaterThan(0);
            }
        }

        for (int i = 0; i < purchaseRate.size(); i++) {
            Map<String , Float> rate = purchaseRate.get(i);
            if(rate.containsKey("purchaseRate")){
                Float purchaseRateValue = rate.get("purchaseRate");
                softAssertions.assertThat(purchaseRateValue).as("Item # "+ i).isGreaterThan(0);
            }

        }
        softAssertions.assertAll();
    }
}
