package bookstoreApiTests;

import bookstoreApi.BookstoreApiHelper;
import bookstoreApi.EndpointBookstore;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class bookstoreApiTests {

    BookstoreApiHelper bookstoreApiHelper = new BookstoreApiHelper();
    String token = bookstoreApiHelper.getToken();
    String userID = bookstoreApiHelper.getUserID();

    @Test
    public void deleteUserBooks(){
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
}
