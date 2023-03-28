package apiTests;

import api.EndPointsForPrivatBank;
import api.ExchangeRateDTO;
import api.PrivatExcRateDTO;
import com.beust.ah.A;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatTest {
   Logger logger = Logger.getLogger(getClass());
    @Test
    public void exchangeCurrencyTest() {
      PrivatExcRateDTO responseAsDTO = given()
              .queryParam("startFrom","22.03.2022")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsForPrivatBank.Privat_CurrencyEX)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatExcRateDTO.class)

               ;
      logger.info("Bank name = " + responseAsDTO.getBank());
      logger.info("Currency name = " + responseAsDTO.getExchangeRate().length);
ExchangeRateDTO[] privatExpected = {
        ExchangeRateDTO.builder().currency("AUD").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("AZN").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("BYN").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("CAD").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("CHF").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("CNY").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("CZK").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("DKK").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("EUR").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("GBL").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("GEL").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("ILS").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("KZT").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("NOK").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("PLN").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("SEK").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("SGT").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("TMT").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("UAH").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("USD").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("UZS").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("HUF").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("JPY").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("MDL").baseCurrency("UAH").build(),
        ExchangeRateDTO.builder().currency("TRY").baseCurrency("UAH").build()

};
      PrivatExcRateDTO expectedResult=
              PrivatExcRateDTO.builder().date("22.03.2022").bank("PB")
                      .baseCurrency(980).baseCurrencyLit("UAH")
                      .exchangeRate(privatExpected).build();

        Assert.assertEquals("Number of currencies",responseAsDTO.getExchangeRate()
                .length,expectedResult.getExchangeRate().length);



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





