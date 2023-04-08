package bookstoreApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;


public class BookstoreApiHelper {
    private final String USER_NAME = "goshia";
    private final String PASSWORD = "Qwe1234!";

    public String getToken() {
        return getToken(USER_NAME, PASSWORD);
    }

    public String getToken(String userName, String password) {
        JSONObject requestParameters = new JSONObject();
        requestParameters.put("userName", userName);
        requestParameters.put("password", password);

        Response responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParameters.toMap())
                        .post(EndpointBookstore.login)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();
        String token = responseBody.jsonPath().getString("token").replace("\"", "");
        return token;
    }

    public String getUserID() {
        return getUserID(USER_NAME, PASSWORD);
    }

    public String getUserID(String userName, String password) {
        JSONObject requestParameters = new JSONObject();
        requestParameters.put("userName", userName);
        requestParameters.put("password", password);

        Response responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParameters.toMap())
                        .post(EndpointBookstore.login)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();
        String userId = responseBody.jsonPath().getString("userId").replace("\"", "");
        return userId;
    }

    public List<BookDTO> getAllBooks() {
        Response listOfBooks =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndpointBookstore.books)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        List<BookDTO> allBooks = listOfBooks.jsonPath().getList("books", BookDTO.class);
        return allBooks;
    }

    public String getBookISBN() {
        List<BookDTO> bookDTOS = getAllBooks();
        return bookDTOS.get(0).getIsbn();
    }

}
