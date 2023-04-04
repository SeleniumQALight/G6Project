package api;

import api.dto.books.LoginBooksDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelperBooks {
    public static String USERNAME = "NataliiaBooks";
    public static String PASSWORD = "Qwerty123!";
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken() {
        return getToken(USERNAME, PASSWORD);
    }

    public String getToken(String username, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("password", password);
        requestParams.put("userName", username);

        LoginBooksDTO responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPointsBooks.LOGIN_BOOKS)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(LoginBooksDTO.class);
        return responseBody.getToken();
    }

    public String getUserID() {
        return getToken(USERNAME, PASSWORD);
    }

    public String getUserID(String username, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("password", password);
        requestParams.put("userName", username);

        LoginBooksDTO responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPointsBooks.LOGIN_BOOKS)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(LoginBooksDTO.class);
        return responseBody.getUserId();
    }
}
