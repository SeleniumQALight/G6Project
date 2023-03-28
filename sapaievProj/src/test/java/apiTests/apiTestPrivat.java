package apiTests;

import api.Endpoints;
import api.EndpointsPrivatBank;
import api.PostDTO;
import api.PrivatBankDTO;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class apiTestPrivat {



    @Test
    public void getPostsByUserTest() {
         given()
                .contentType(ContentType.JSON).queryParam("date","22.03.2022")
                .log().all()
                .when()
                .get(EndpointsPrivatBank.requestPrivat)
                .then()
                .statusCode(200)
                .log().all()
                 .extract()
                 .response().as(PrivatBankDTO.class)

         ;

    }
}
