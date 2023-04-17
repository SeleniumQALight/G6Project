package apiTest;

import api.ApiHelperBookStore;
import api.EndPointsBookShop;
import api.dto.bookStore.*;
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
    String token;
    String userId;

    @Before
    public void deleteAllBookFromStore() {
        LoginBooksDTO loginBooksDTO = apiHelperBookStore.getLoginResponse();
        token = loginBooksDTO.getToken();
        userId = loginBooksDTO.getUserId();
        apiHelperBookStore.deleteAllBooksOnUserWithToken(token, userId);
    }

    @Test
    public void checkAttachedBooks() {

        ListBooksDto listOfAllBooks = apiHelperBookStore.getListOfAllBooks();

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
                        .post(EndPointsBookShop.BOOK_STORE)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response();

        List<IsbnBooksDTO> addedBook = response.jsonPath().getList("books", IsbnBooksDTO.class);

        assertEquals("The number of books is incorrect ", 1, addedBook.size());
        assertEquals("Number isbn is incorrect ", firstIsbn, addedBook.get(0).getIsbn());

        List<BooksDTO> listOfAllBooksAfter = apiHelperBookStore.getActualUserBooks(userId, token);

        assertEquals("The number of books is incorrect ", 1, listOfAllBooksAfter.size());
        assertEquals("Number isbn is incorrect ", firstIsbn, listOfAllBooksAfter.get(0).getIsbn());
    }
}
