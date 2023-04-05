package apiTest;

import api.EndPointsForPrivatBank;
import api.ExchangeRateDTO;
import api.PrivatBankDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static api.ExchangeRateDTO.createExchangeRateWithUAHAsDefault;
import static io.restassured.RestAssured.given;

public class PrivatBankTest {

    private Logger logger = Logger.getLogger(getClass());
    String BANK_NAME = "PB";
    int BASE_CURRENCY = 980;
    String BASE_CUR_LIST = "UAH";
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String formattedDate = currentDate.format(formatter);



    @Test
    public void currentCurrenciesCourse() {
        PrivatBankDTO responseAsDto = given().contentType(ContentType.JSON)
                .queryParam("date", formattedDate)
                .log().all()
                .when()
                .get(EndPointsForPrivatBank.GET_CURRENCY)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatBankDTO.class);

        List<ExchangeRateDTO> listOfAllRates = new ArrayList<>();

        List<String> listOfCurrency =List.of
                        ("AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "PLN", "SEK",
                        "SGD", "TMT", "TRY", "UAH", "USD", "UZS", "GBP", "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK");

        for (String currency : listOfCurrency) {
            listOfAllRates.add(createExchangeRateWithUAHAsDefault(currency));
        }

        PrivatBankDTO expectedResult = PrivatBankDTO.builder()
                .date(formattedDate)
                .bank(BANK_NAME)
                .baseCurrency(BASE_CURRENCY)
                .baseCurrencyLit(BASE_CUR_LIST)
                .exchangeRate(listOfAllRates)
                .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB","exchangeRate.purchaseRateNB","exchangeRate.saleRate","exchangeRate.purchaseRate" )
                .isEqualTo(expectedResult);

        softAssertions.assertAll();

    }

}
