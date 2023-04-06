package bookstoreApiTests;

import bookstoreApi.BooksDTO;
import bookstoreApi.BookstoreApiHelper;
import bookstoreApi.EndpointBookstore;
import bookstoreApi.UsersIsbnsOfBooks;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BookstoreApiTest {

    BookstoreApiHelper bookstoreApiHelper = new BookstoreApiHelper();
    String token = bookstoreApiHelper.getToken();
    String userID = bookstoreApiHelper.getUserID();

    @Test
    public void deleteUserBooks() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .when()
                .delete(EndpointBookstore.deleteBooks, bookstoreApiHelper.getUserID())
                .then()
                .statusCode(204)
                .log().all();
    }

    @Test
        public void addBook(){
            BooksDTO listOfBooks =
                    given()
                            .contentType(ContentType.JSON)
                            .log().all()
                            .when()
                            .get(EndpointBookstore.books)
                            .then()
                            .statusCode(200)
                            .log().all()
                            .extract().response().as(BooksDTO.class);

            List<UsersIsbnsOfBooks> usersIsbnsOfBooks = new ArrayList<>();


    }


}
