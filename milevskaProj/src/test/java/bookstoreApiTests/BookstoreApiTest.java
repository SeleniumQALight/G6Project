package bookstoreApiTests;

import bookstoreApi.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookstoreApiTest {
    BookstoreApiHelper bookstoreApiHelper = new BookstoreApiHelper();
    String token = bookstoreApiHelper.getToken();
    String userID = bookstoreApiHelper.getUserID();
    String isbn = bookstoreApiHelper.getBookISBN();


    @Test
    public void deleteUserBooks() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .when()
                .delete(EndpointBookstore.deleteBooks, userID)
                .then()
                .statusCode(204)
                .log().all();
    }

    @Test
    public void addBookInUserProfile() {
        List<CollectionOfIsbnsDTO> collectionOfIsbnsList = List.of(CollectionOfIsbnsDTO.builder().isbn(isbn).build());

        AddBookToUserProfileDTO addBookToUserProfileDTO = AddBookToUserProfileDTO.builder().userId(userID).collectionOfIsbns(collectionOfIsbnsList).build();


        Response addBook =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .auth().oauth2(token)
                        .body(addBookToUserProfileDTO)
                        .when()
                        .post(EndpointBookstore.books)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response();

        List<BookDTO> response = addBook.jsonPath().getList("books", BookDTO.class);

        Assert.assertEquals("User add not correct count of books", 1, response.size());
        Assert.assertEquals("User add not this book", isbn, response.get(0).getIsbn());

    }
}
