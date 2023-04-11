package api;

import api.BooksStoreDtos.*;
import io.restassured.http.ContentType;
import lombok.Getter;

import java.util.List;

import static io.restassured.RestAssured.given;

@Getter
public class BooksStoreApiHelper {
    public static final String LOGIN = "Kovatest";
    public static final String PASSWORD = "Kovatest123456!";
    private String userId;
    private String token;

    public BooksStoreApiHelper() {
        UserLoginResponseDTO responseDTO = authorizeUser();
        this.userId = responseDTO.getUserId();
        this.token = responseDTO.getToken();
    }

    public List<Book> getAllBooksInStore() {
        StoreBooksResponseDTO books = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(BooksStoreApi.BOOKS)
                .then()
                .statusCode(200)
                .log().all()
                .extract().as(StoreBooksResponseDTO.class);

        return books.getBooks();
    }

    public void deleteAllBooksForUser() {
        given().contentType(ContentType.JSON)
                .auth().oauth2(token)
                .log().all()
                .when()
                .delete(BooksStoreApi.DELETE_BOOKS, userId)
                .then()
                .log().all()
                .statusCode(204);

    }

    public UserProfile getUserProfile() {
        return given().contentType(ContentType.JSON)
                .auth().oauth2(token)
                .log().all()
                .when()
                .get(BooksStoreApi.USER_PROFILE, userId)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response()
                .as(UserProfile.class);
    }

    private UserLoginResponseDTO authorizeUser() {
        UserLoginResponseDTO responseDTO = given().contentType(ContentType.JSON)
                .log().all()
                .body(UserLoginRequestDTO.builder()
                        .userName(BooksStoreApiHelper.LOGIN)
                        .password(BooksStoreApiHelper.PASSWORD)
                        .build())
                .when()
                .post(BooksStoreApi.LOGIN)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response()
                .as(UserLoginResponseDTO.class);
        return responseDTO;
    }

}
