package apiTests;

import api.*;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class ApiTestsPrivat {
    final String DATE = "01.12.2014";
    final Integer NUMBERBANKS = 19;
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRatesByDate() {
              BankDTO responseAsDTO = given()
                        .contentType(ContentType.JSON)
                        .log().all()
                      .when()
                        .get(EndPointsPrivat.getExchangeRates,DATE)
                      .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(BankDTO.class)
                      ;
              logger.info("Number of currency " + responseAsDTO.getExchangeRate().length);
              logger.info("Name of Bank is " + responseAsDTO.getBank());
              logger.info("Base currency is " + responseAsDTO.getBaseCurrency());

        for (int i = 0; i < responseAsDTO.getExchangeRate().length; i++) {
            Assert.assertEquals( "Base currency is not matched in row: " + i
            ,"UAH", responseAsDTO.getExchangeRate()[i].getBaseCurrency());
        }

        BankDTO expectedResult =
                new BankDTO("01.12.2014", "PB", 980, "UAH");


        Assert.assertEquals("Number of banks " + NUMBERBANKS, "Number of banks " + responseAsDTO.getExchangeRate().length);

        SoftAssertions softAssertions = new SoftAssertions();
    }
}
