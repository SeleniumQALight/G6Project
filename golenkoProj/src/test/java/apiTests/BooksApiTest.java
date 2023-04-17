package apiTests;

import api.ApiHelperBooks;
import api.EndPointsBooks;
import api.dto.books.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BooksApiTest {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    String token = apiHelperBooks.getToken();
    String userID = apiHelperBooks.getUserID();

    @Before
    public void deleteBooksForUser() {

        given()
                .contentType(ContentType.JSON)
                .queryParams("UserId", userID)
                .log().all()
                .auth().oauth2(token)
                .when()
                .delete(EndPointsBooks.DELETE_BOOKS)
                .then()
                .statusCode(204)
                .log().all();

        Assert.assertEquals("Books were not deleted", 0, apiHelperBooks.getActualUserBooks(token, userID).size());
    }

    @Test
    public void checkBookAdding() {
        BooksListDTO listOfAllBooks =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPointsBooks.BOOKS)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(BooksListDTO.class);

        String firstIsbn = listOfAllBooks.getBooks().get(0).getIsbn();

        List<IsbnDTO> collectionIsbn = new ArrayList<>();
        collectionIsbn.add(new IsbnDTO(firstIsbn));

        AddBookDTO addOneBook = AddBookDTO.builder()
                .userId(userID)
                .collectionOfIsbns(collectionIsbn)
                .build();

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .auth().oauth2(token)
                        .body(addOneBook)
                        .when()
                        .post(EndPointsBooks.BOOKS)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response();

        List<IsbnDTO> addedBook = response.jsonPath().getList("books", IsbnDTO.class);

        Assert.assertEquals("Incorrect number of books", 1, addedBook.size());
        Assert.assertEquals("Incorrect isbn number", firstIsbn, addedBook.get(0).getIsbn());


        List<BookDTO> actualUserBooks = apiHelperBooks.getActualUserBooks(token, userID);

        Assert.assertEquals("There is incorrect number of books for user", 1, actualUserBooks.size());
        Assert.assertEquals("Incorrect isbn", firstIsbn, actualUserBooks.get(0).getIsbn());

    }
}
