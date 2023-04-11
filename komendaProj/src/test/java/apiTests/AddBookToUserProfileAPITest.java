package apiTests;

import api.ApiHelperAddBook;
import api.EndPoints;
import api.dto.books.AddBooksDTO;
import api.dto.books.IsbnBooksDTO;
import api.dto.books.ListBooksDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddBookToUserProfileAPITest {
    ApiHelperAddBook apiHelperAddBook = new ApiHelperAddBook();
    String token = apiHelperAddBook.getToken();
    String userId = apiHelperAddBook.getUserID();
    Logger logger = Logger.getLogger(getClass());

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
                .collectionOfIsbns(collectionIsbn)
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

        Assert.assertEquals("The number of books is incorrect ", 1, addedBook.size());
        Assert.assertEquals("Number isbn is incorrect ", firstIsbn, addedBook.get(0).getIsbn());
    }
}
