package apiTests;

import api.Endpoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class apiTests {
    final String USER_NAME="autoapi";

    @Test
    public void getPostsByUserTest(){
    given()
            .contentType(ContentType.JSON)
            .log().all()
            .when()
            .get(Endpoints.POST_BY_USER,USER_NAME)
            .then()
            .statusCode(200)

    ;
    }
}
