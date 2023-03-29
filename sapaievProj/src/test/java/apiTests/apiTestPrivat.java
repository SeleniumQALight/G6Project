package apiTests;

import api.*;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class apiTestPrivat {


    @Test
    public void getPostsByUserTest() {
        PrivatBankDTO responseObjectDTO = given()
                .contentType(ContentType.JSON).queryParam("date", "22.03.2022")
                .log().all()
                .when()
                .get(EndpointsPrivatBank.requestPrivat)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatBankDTO.class);

        Assert.assertTrue("", responseObjectDTO.getExchangeRate().length == 25);

        PrivatBankDTO expectedResult = new PrivatBankDTO("PB", "UAH",

                new ExchangeRateDTO[]{
                        new
                                ExchangeRateDTO("UAH", "AUD"),
                        new

                                ExchangeRateDTO("UAH", "AZN"),
                        new

                                ExchangeRateDTO("UAH", "BYN"),
                        new

                                ExchangeRateDTO("UAH", "CAD"),
                        new

                                ExchangeRateDTO("UAH", "CHF"),
                        new

                                ExchangeRateDTO("UAH", "CNY"),
                        new

                                ExchangeRateDTO("UAH", "CZK"),
                        new

                                ExchangeRateDTO("UAH", "DKK"),
                        new

                                ExchangeRateDTO("UAH", "EUR"),
                        new

                                ExchangeRateDTO("UAH", "GBP"),
                        new

                                ExchangeRateDTO("UAH", "GEL"),
                        new

                                ExchangeRateDTO("UAH", "HUF"),

                        new

                                ExchangeRateDTO("UAH", "ILS"),
                        new

                                ExchangeRateDTO("UAH", "JPY"),
                        new

                                ExchangeRateDTO("UAH", "KZT"),
                        new

                                ExchangeRateDTO("UAH", "MDL"),
                        new

                                ExchangeRateDTO("UAH", "NOK"),
                        new

                                ExchangeRateDTO("UAH", "PLN"),
                        new

                                ExchangeRateDTO("UAH", "SEK"),
                        new

                                ExchangeRateDTO("UAH", "SGD"),
                        new

                                ExchangeRateDTO("UAH", "TMT"),
                        new

                                ExchangeRateDTO("UAH", "TRY"),
                        new

                                ExchangeRateDTO("UAH", "UAH"),
                        new

                                ExchangeRateDTO("UAH", "USD"),
                        new

                                ExchangeRateDTO("UAH", "UZS")

                });


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(responseObjectDTO)
                .usingRecursiveComparison()
                .ignoringFields(
                        "date", "baseCurrency",
                        "exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResult);


        softAssertions.assertAll();

    }
}
