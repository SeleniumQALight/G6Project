package api;

import api.dto.responseDto.PrivateBankDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivateBank {
    private static final int COURSE_ID = 5;
    Logger logger = Logger.getLogger(getClass());
    public List<PrivateBankDTO> getListOfCurrenciesById() {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("json", "")
                        .queryParam("exchange", "")
                        .queryParam("coursid", COURSE_ID)
                        .log().all()
                        .when()
                        .get(EndPointsPB.CURRENCY_EXCHANGE_RATE_ON_ID)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        return response.jsonPath().getList("",PrivateBankDTO.class);
    }

    public void getCurrencyExchangeRate(String currency) {
        List<PrivateBankDTO> listOfCurrenciesAPI = getListOfCurrenciesById();

        for (PrivateBankDTO PrivateBankDTO: listOfCurrenciesAPI) {
            if (PrivateBankDTO.getCcy().equalsIgnoreCase(currency)) {
                TestData.exchangeRatesApiBuy = PrivateBankDTO.getBuy();
                TestData.exchangeRateApiSell = PrivateBankDTO.getSale();
            }
        }
        if (TestData.exchangeRatesApiBuy == null || TestData.exchangeRateApiSell == null) {
            logger.info ("The exchange rate for currency " + currency +  "is not found");
            Assert.fail("The exchange rate for currency " + currency +  "is not found");
        }
    }
}
