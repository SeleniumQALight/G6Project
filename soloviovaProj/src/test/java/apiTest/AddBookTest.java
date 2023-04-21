package apiTest;

import api.APIHelperBookStore;
import api.EndPointsBooks;
import api.booksDTO.AddBooksDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddBookTest {
    APIHelperBookStore apiHelperBookStore = new APIHelperBookStore();
    final String USER_NAME = "YuliiaSol";
    final String PASSWORD = "Qwerty1234567890!";
    String token = apiHelperBookStore.loginResponse(USER_NAME, PASSWORD).getToken();
    String userId = apiHelperBookStore.loginResponse(USER_NAME, PASSWORD).getUserId();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllBooks() {
        apiHelperBookStore.deleteBooks(token, userId, USER_NAME, PASSWORD);
    }


    @Test
    public void addBookTest() {
        JSONObject getIsBn = apiHelperBookStore.getIsbnOfFirstBook();

        List<Map> isBns = new ArrayList<>();
        isBns.add(getIsBn.toMap());

        AddBooksDTO addBooks = AddBooksDTO.builder()
                .userId(userId)
                .collectionOfIsbns(isBns)
                .build();

        given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .log().all()
                .body(addBooks)
                .when()
                .post(EndPointsBooks.bookStore)
                .then()
                .statusCode(201);

        Assert.assertEquals("Number of books in basket is ", 1, apiHelperBookStore.userBasket(USER_NAME, PASSWORD).size());
        Assert.assertEquals("isbn number is ", apiHelperBookStore.userBasket(USER_NAME, PASSWORD).get(0).get("isbn"), isBns.get(0).get("isbn"));
        logger.info("the value is " + apiHelperBookStore.userBasket(USER_NAME, PASSWORD).get(0).get("isbn") + " and " + isBns.get(0).get("isbn"));

    }
}
