package apiTest;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    final  String USER_NAME = "autoapi";
    @Test
    public void getPostByUserTest() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME )// action
                .then()
                .statusCode(200)
                .log().all()
         ;

    }
}
