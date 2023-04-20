package api;

import api.dto.bookStore.BooksDTO;
import api.dto.bookStore.ListBooksDto;
import api.dto.bookStore.LoginBooksDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class ApiHelperBookStore {
    public static final String USER_NAME = "test111";
    public static final String PASSWORD = "123456QwErTy!";

    public LoginBooksDTO getLoginResponse() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", USER_NAME);
        requestParams.put("password", PASSWORD);

        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams.toMap())
                .when()
                .post(EndPointsBookShop.LOGIN_BOOK)
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
                .delete(EndPointsBookShop.BOOK_STORE)
                .then()
                .statusCode(204)
                .log().all()
                .extract().statusCode();
        assertTrue("The number of books is incorrect ", getActualUserBooks(userId, token).isEmpty());
    }

    public ListBooksDto getListOfAllBooks() {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsBookShop.BOOK_STORE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(ListBooksDto.class);
    }

    public List<BooksDTO> getActualUserBooks(String userId, String token) {
        Response responseGetBooks =
                given()
                        .auth().oauth2(token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPointsBookShop.USER_BOOK, userId)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        return responseGetBooks.jsonPath().getList("books", BooksDTO.class);
    }
}
