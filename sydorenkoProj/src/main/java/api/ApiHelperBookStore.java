package api;

import api.dto.bookStore.BooksDTO;
import api.dto.bookStore.LoginBooksDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperBookStore {
    public static final String USER_NAME = "Eugen";
    public static final String PASSWORD = "Qwer123!";

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

    public void deleteAllBooksOnUserWithToken(String token, String userId) {
        given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .queryParam("UserId", userId)
                .log().all()
                .when()
                .delete(EndPoints.BOOK_STORE)
                .then()
                .statusCode(204)
                .log().all()
                .extract().statusCode();
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
