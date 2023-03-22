package apiTests;

import api.BPExchangeRateDTO;
import api.Endpoinds;
import api.PBExchangeDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PrivatBankTest {
    final String EXCHANGE_RATE_DATE = "01.12.2014";

    @Test
    public void getExchangeRateByDate() {
        PBExchangeDTO responseAsDTO =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(Endpoinds.PB_EXCHANGE_RATE_URL, EXCHANGE_RATE_DATE)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .as(PBExchangeDTO.class);

        BPExchangeRateDTO[] expectedResultExchangeRateDTO = {
                new BPExchangeRateDTO("UAH", "AUD"),
                new BPExchangeRateDTO("UAH", "CAD"),
                new BPExchangeRateDTO("UAH", "CZK"),
                new BPExchangeRateDTO("UAH", "DKK"),
                new BPExchangeRateDTO("UAH", "HUF"),
                new BPExchangeRateDTO("UAH", "ILS"),
                new BPExchangeRateDTO("UAH", "JPY"),
                new BPExchangeRateDTO("UAH", "LVL"),
                new BPExchangeRateDTO("UAH", "LTL"),
                new BPExchangeRateDTO("UAH", "NOK"),
                new BPExchangeRateDTO("UAH", "SKK"),
                new BPExchangeRateDTO("UAH", "SEK"),
                new BPExchangeRateDTO("UAH", "CHF"),
                new BPExchangeRateDTO("UAH", "GBP"),
                new BPExchangeRateDTO("UAH", "USD"),
                new BPExchangeRateDTO("UAH", "BYR"),
                new BPExchangeRateDTO("UAH", "EUR"),
                new BPExchangeRateDTO("UAH", "GEL"),
                new BPExchangeRateDTO("UAH", "PLZ")
        };

        PBExchangeDTO expectedResultExchangeDTO = new PBExchangeDTO(
                "01.12.2014"
                , "PB"
                , 980
                , "UAH"
                , expectedResultExchangeRateDTO
        );

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseAsDTO).isEqualToIgnoringGivenFields(expectedResultExchangeDTO, "exchangeRate");

        for (int i = 0; i < expectedResultExchangeRateDTO.length; i++) {
            softAssertions.assertThat(responseAsDTO.getExchangeRate()[i]).isEqualToIgnoringGivenFields(expectedResultExchangeDTO.getExchangeRate()[i]
                    , "saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate");
        }

        softAssertions.assertAll();

    }
}
