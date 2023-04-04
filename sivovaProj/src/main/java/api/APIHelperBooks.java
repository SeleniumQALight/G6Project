package api;

import api.dto.responseDto.BookDTO;
import api.dto.responseDto.PostDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class APIHelperBooks {


    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        Response response =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPointsBooks.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();


        String token = response.jsonPath().getString("token");
        return token;
    }

    public String getUserId(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        Response response =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPointsBooks.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();


        String userId = response.jsonPath().getString("userId");
        return userId;
    }

    private BookDTO[] getAllBooks () {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsBooks.BOOKSTORE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(BookDTO[].class);
    }


    private BookDTO[] getAllBooksByUser (String userId){
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsBooks.BOOKSTORE, userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(BookDTO[].class);
    }
    public void deleteBooksTillPresent(String username, String password) {
        String token = getToken(username, password);
        String userId = getUserId(username, password);
        BookDTO[] listOfBooks = getAllBooksByUser(userId);


    }
}
