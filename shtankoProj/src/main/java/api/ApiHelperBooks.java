package api;

import api.dto.booksDTO.BooksDTO;
import api.dto.booksDTO.LogInDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.*;

import io.restassured.response.Response;
import io.restassured.specification.*;
import org.json.JSONObject;

import java.util.List;


import static io.restassured.RestAssured.given;

public class ApiHelperBooks {
    public static String USER_NAME = "marinaS";
    public static String PASSWORD = "Qwerty123456@";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    public LogInDTO getLoginResponse(String username, String password){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestParams.toMap())
                .when()
                .post(EndPointsBooks.LOGIN)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response()
                .getBody().as(LogInDTO.class);
    }

    public String getToken(){
        return getLoginResponse(USER_NAME, PASSWORD).getToken();
    }
    public String getUserID(){
        return  getLoginResponse(USER_NAME,PASSWORD).getUserId();
    }

    public List<BooksDTO> getActualBooks(String token, String userID){
        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .when()
                .get(EndPointsBooks.USER_BOOKS, userID)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response.jsonPath().getList("books", BooksDTO.class);
    }


}
