package apiTests;

import api.ApiHelperBooks;
import api.EndPointsBooks;
import api.dto.books.AddBookDTO;
import api.dto.books.BooksListDTO;
import api.dto.books.IsbnDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestBooks {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    Logger logger = Logger.getLogger(getClass());
    String token = apiHelperBooks.getToken();
    String userID = apiHelperBooks.getUserID();

    @Before
    public void deleteBooksForUser() {

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .when()
                .delete(EndPointsBooks.DELETE_BOOKS, userID)
                .then()
                .statusCode(204)
                .log().all();
    }


    @Test
    public void checkBookAdding() {
        BooksListDTO listOfAllBooks =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPointsBooks.LIST_OF_ALL_BOOKS)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(BooksListDTO.class);

        String isbn = listOfAllBooks.getBooks().get(0).getIsbn();

        List<IsbnDTO> collectionIsbn = new ArrayList<>();
        collectionIsbn.add(new IsbnDTO(isbn));

        AddBookDTO addOneBook = AddBookDTO.builder()
                .userId(userID)
                .collectionOfIsbns(collectionIsbn)
                .build();


//        String response =
//                given()
//                        .contentType(ContentType.JSON)
//                        .log().all()
//                        .when()


    }

}
