package api;

import api.dto.books.LoginBooksDTO;
import api.dto.books.BooksDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperAddBook {
    public static final String USER_NAME = "wk_test";
    public static final String PASSWORD = "12345qQ!";
    Logger logger = Logger.getLogger(getClass());

    public String getToken() {
        return getLoginResponse(USER_NAME, PASSWORD).getToken();
    }

    public String getUserID() {
        return getLoginResponse(USER_NAME, PASSWORD).getUserId();
    }

    public LoginBooksDTO getLoginResponse(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams.toMap())
                .when()
                .post(EndPoints.LOGIN_BOOK)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(LoginBooksDTO.class);
    }

    public List<BooksDTO> getActualUserBooks(String token, String userId) {
        Response responseGetBooks =
                given()
                        .auth().oauth2(token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.LOGIN_BOOK, userId)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        return responseGetBooks.jsonPath().getList("books", BooksDTO.class);
    }
}
