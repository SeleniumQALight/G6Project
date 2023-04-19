package apiTests;

import api.EndPointsForPrivatBank;
import api.ExchangeRateDTO;
import api.PrivatExcRateDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatTest {
   Logger logger = Logger.getLogger(getClass());
   String responseDate = "22.03.2022";
    @Test
    public void exchangeCurrencyTest() {
        PrivatExcRateDTO responseAsDTO = given()
                .contentType(ContentType.JSON)
                .queryParam("date", responseDate)
                .log().all()
                .when()
                .get(EndPointsForPrivatBank.Privat_CurrencyEX)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatExcRateDTO.class);
        logger.info("Bank name = " + responseAsDTO.getBank());
        logger.info("Currency name = " + responseAsDTO.getExchangeRate().length);


        ExchangeRateDTO[] privatExpected = {ExchangeRateDTO.builder()
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
                        .baseCurrency("UAH").currency("UZS").build()};

      PrivatExcRateDTO expectedResult = PrivatExcRateDTO.builder().date("22.03.2022").bank("PB")
                      .baseCurrency(980).baseCurrencyLit("UAH")
                      .exchangeRate(privatExpected).build();

        Assert.assertEquals("Number of currencies",responseAsDTO.getExchangeRate()
                .length,expectedResult.getExchangeRate().length);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseAsDTO)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB","exchangeRate.purchaseRateNB"
                        ,"exchangeRate.saleRate","exchangeRate.purchaseRate")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();


    }

@Test
    public void getResponseSchema(){
    given()
            .queryParam("startFrom","22.03.2022")
            .contentType(ContentType.JSON)
            .log().all()
            .when()
            .get(EndPointsForPrivatBank.Privat_CurrencyEX)
            .then()
            .statusCode(200)
            .log().all()
            .assertThat().body(matchesJsonSchemaInClasspath("data-privatResponse.json"));
}


}





