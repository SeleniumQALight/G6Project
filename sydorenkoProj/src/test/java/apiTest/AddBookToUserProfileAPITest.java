package apiTest;

import api.ApiHelperBookStore;
import api.EndPoints;
import api.dto.bookStore.AddBooksDTO;
import api.dto.bookStore.IsbnBooksDTO;
import api.dto.bookStore.ListBooksDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AddBookToUserProfileAPITest {
    ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();
    String token = apiHelperBookStore.getToken();
    String userId = apiHelperBookStore.getUserID();

    @Before
    public void deleteAllBookFromStore() {
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

    @Test
    public void checkAttachedBooks() {
        ListBooksDto listOfAllBooks =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.BOOK_STORE)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(ListBooksDto.class);

        String firstIsbn = listOfAllBooks.getBooks().get(0).getIsbn();

        List<IsbnBooksDTO> collectionIsbn = new ArrayList<>();
        collectionIsbn.add(new IsbnBooksDTO(firstIsbn));

        AddBooksDTO addOneBook = AddBooksDTO.builder()
                .userId(userId)
                .collectionOfIsbn(collectionIsbn)
                .build();

        Response response =
                given()
                        .auth().oauth2(token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(addOneBook)
                        .when()
                        .post(EndPoints.BOOK_STORE)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response();

        List<IsbnBooksDTO> addedBook = response.jsonPath().getList("books", IsbnBooksDTO.class);

        assertEquals("The number of books is incorrect ", 1, addedBook.size());
        assertEquals("Number isbn is incorrect ", firstIsbn, addedBook.get(0).getIsbn());
    }
}
