package apiTests;

import api.Endpoinds;
import api.dto.requestDTO.AddListOfBooksDTO;
import api.dto.requestDTO.CollectionOfIsbnsDTO;
import api.helpers.ApiHelperDemoQa;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class BookTest {
    ApiHelperDemoQa apiHelperDemoQa = new ApiHelperDemoQa();

    String userId = apiHelperDemoQa.getUserId();
    String userToken = apiHelperDemoQa.getToken();

    @Before
    public void deleteAllUserBooks() {
                given()
                        .auth().oauth2(userToken)
                        .contentType(ContentType.JSON)
                        .queryParam("UserId", userId)
                        .log().all()
                        .when()
                        .delete(Endpoinds.DEMO_QA_BOOK_STORE)
                        .then()
                        .statusCode(204)
                        .log().all()
                        .extract().statusCode();
    }

    @Test
    public void Book() {
        Response actualResponseListOfBooks =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(Endpoinds.DEMO_QA_BOOK_STORE)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        List<String> listOfBooks = actualResponseListOfBooks.jsonPath().getList("books.isbn", String.class);
        String firstBookIsbn = listOfBooks.get(0);

        CollectionOfIsbnsDTO[] createCollectionOfIsbns = {CollectionOfIsbnsDTO.builder().isbn(firstBookIsbn).build()};

        AddListOfBooksDTO requestAddListOfBooks = AddListOfBooksDTO.builder()
                .userId(userId)
                .collectionOfIsbns(createCollectionOfIsbns)
                .build();

        Response actualListOfUserBooks =
                given()
                        .auth().oauth2(userToken)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestAddListOfBooks)
                        .when()
                        .post(Endpoinds.DEMO_QA_BOOK_STORE)
                        .then()
                        .statusCode(201)
                        .extract().response();

        List<String> actualCountOfUserBooks = actualListOfUserBooks.jsonPath().getList("books.isbn");

        Assert.assertEquals("Actual count of user books ", 1, actualCountOfUserBooks.size());
        Assert.assertEquals("Isbn doesn't match", firstBookIsbn, actualCountOfUserBooks.get(0));


    }
}
