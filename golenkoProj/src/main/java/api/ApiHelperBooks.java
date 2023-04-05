package api;

import api.dto.books.BookDTO;
import api.dto.books.LoginBooksDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperBooks {
    public static String USERNAME = "NataliiaBooks";
    public static String PASSWORD = "Qwerty123!";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public LoginBooksDTO getLoginResponse(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("password", password);
        requestParams.put("userName", userName);

        return given()
                .spec(requestSpecification)
                .body(requestParams.toMap())
                .when()
                .post(EndPointsBooks.LOGIN_BOOKS)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response()
                .getBody()
                .as(LoginBooksDTO.class);

    }

    public String getToken() {
        return getLoginResponse(USERNAME, PASSWORD).getToken();
    }

    public String getUserID() {
        return getLoginResponse(USERNAME, PASSWORD).getUserId();
    }

    public List<BookDTO> getActualUserBooks(String token, String userID) {
        Response responseGet =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .auth().oauth2(token)
                        .when()
                        .get(EndPointsBooks.USER_BOOKS, userID)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        return responseGet.jsonPath().getList("books", BookDTO.class);
    }

}
