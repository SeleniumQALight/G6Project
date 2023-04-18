package apiTest;

import api.APIHelperBookStore;
import api.EndPointsBooks;
import api.booksDTO.AddBooksDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
    String token = apiHelperBookStore.getToken();
    String userId = apiHelperBookStore.getUserID();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteBooks() {
        given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .delete(EndPointsBooks.deleteBooks, userId)
                .then()
                .statusCode(204)
                .log().all();
    }

    @Test
    public void addBookTest() {
        JSONObject getIsBn = apiHelperBookStore.getIsbnBook();

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


        Response basketResponse = given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsBooks.userAccount, userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        List<Map> userIsBnList = basketResponse.jsonPath().getList("books", Map.class);
        Assert.assertEquals("Number of books in basket is ", 1, userIsBnList.size());
        Assert.assertEquals("isbn number is ", userIsBnList.get(0).get("isbn"), isBns.get(0).get("isbn"));
        logger.info("the value is " + userIsBnList.get(0).get("isbn") + " and " + isBns.get(0).get("isbn"));

    }
}
