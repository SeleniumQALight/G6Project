package apiTests;

import api.Endpoinds;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {
    final String USER_NAME = "autoapi";
    @Test
    public void getPostsByUserTest(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoinds.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all();
    }
}
