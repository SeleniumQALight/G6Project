package api;

import api.booksDTO.LoginResponseDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIHelperBookStore {

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

    public List<Map> userBasket(String userName, String password){
        Response basketResponse = given()
                .auth().oauth2(loginResponse(userName, password).getToken())
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsBooks.userAccount, loginResponse(userName, password).getUserId())
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
       return basketResponse.jsonPath().getList("books", Map.class);
    }

    public JSONObject getIsbnOfFirstBook() {
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

    public void deleteBooks(String token, String userId, String userName, String password){
        given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .queryParam("json").queryParam("UserId", userId)
                .log().all()
                .when()
                .delete(EndPointsBooks.bookStore)
                .then()
                .statusCode(204)
                .log().all();

        Assert.assertEquals("", 0, userBasket(userName, password).size());
    }

}
