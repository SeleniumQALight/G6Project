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

        Assert.assertEquals("Number of user books:", 0, apiHelperDemoQa.getAllUserBooks(userToken, userId)
                .jsonPath().getList("books").size());
    }

    @Test
    public void Book() {
        List<String> listOfBooks = apiHelperDemoQa.getAllListOfBooks().jsonPath().getList("books.isbn", String.class);
        String firstBookIsbn = listOfBooks.get(0);

        CollectionOfIsbnsDTO[] createCollectionOfIsbns = {CollectionOfIsbnsDTO.builder().isbn(firstBookIsbn).build()};

        AddListOfBooksDTO requestAddListOfBooks = AddListOfBooksDTO.builder()
                .userId(userId)
                .collectionOfIsbns(createCollectionOfIsbns)
                .build();

        apiHelperDemoQa.addListOfBooksToUser(userToken, requestAddListOfBooks);

        Response actualCountOfUserBooks = apiHelperDemoQa.getAllUserBooks(userToken, userId);

        Assert.assertEquals("Actual count of user books ", 1,
                actualCountOfUserBooks.jsonPath().getList("books").size());
        Assert.assertEquals("Isbn doesn't match ", firstBookIsbn,
                actualCountOfUserBooks.jsonPath().getList("books.isbn").get(0));
    }
}
