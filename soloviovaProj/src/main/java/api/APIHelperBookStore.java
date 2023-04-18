package api;

import api.booksDTO.LoginResponseDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class APIHelperBookStore {
    public static final String USER_NAME = "YuliiaSol";
    final String PASSWORD = "Qwerty1234567890!";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public LoginResponseDTO loginResponse(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);
        return given().spec(requestSpecification)
                .body(requestParams.toMap())
                .when()
                .post(EndPointsBooks.loginBookstore)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(LoginResponseDTO.class);
    }

    public String getToken() {
        return loginResponse(USER_NAME, PASSWORD).getToken();
    }

    public String getUserID() {
        return loginResponse(USER_NAME, PASSWORD).getUserId();
    }

    public JSONObject getIsbnBook() {
        Response response = given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsBooks.bookStore)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isbn", response.jsonPath().getString("books[0].isbn"));

        return jsonObject;
    }

}
