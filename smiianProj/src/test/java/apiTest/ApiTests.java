package apiTest;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    final String USER_NAME = "autoapi";

    @Test
    public void getPostByUser() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
             .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)     //це і попередні рядки відправить GET на endpoint
             .then()
                .statusCode(200)                        // очікуєм відповідь
                .log().all()
                ;
    }
}
