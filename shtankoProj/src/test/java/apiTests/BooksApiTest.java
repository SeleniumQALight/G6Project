package apiTests;

import api.ApiHelperBooks;
import api.EndPointsBooks;
import api.dto.booksDTO.AddBooksDTO;
import api.dto.booksDTO.BooksDTO;
import api.dto.booksDTO.BooksListDTO;
import api.dto.booksDTO.ISBNDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BooksApiTest {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    String token = apiHelperBooks.getToken();
    String userID = apiHelperBooks.getUserID();

    @Before
    public void deleteBooks(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("userId", userID)
                .log().all()
                .auth().oauth2(token)
                .when()
                .delete(EndPointsBooks.DELETE_BOOKS)
                .then()
                .statusCode(204)
                .log().all();
        Assert.assertEquals("Books are not deleted", 0,apiHelperBooks.getActualBooks(token, userID).size());
    }

    @Test
    public void addBooksTest(){
        BooksListDTO booksListDTO = given()
                .contentType(ContentType.JSON)
                .log().all()
                .get(EndPointsBooks.BOOKS)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(BooksListDTO.class);
        String ISBN = booksListDTO.getBooks().get(0).getIsbn();
        List<ISBNDTO> listISBN = new ArrayList<>();
        listISBN.add(new ISBNDTO(ISBN));
        AddBooksDTO addBooks = AddBooksDTO.builder()
                .userId(userID)
                .collectionOfIsbns(listISBN)
                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .body(addBooks)
                .when()
                .post(EndPointsBooks.BOOKS)
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();
        List<ISBNDTO> addedBooks = response.jsonPath().getList("books", ISBNDTO.class);
        Assert.assertEquals("Unverified book number", 1, addedBooks.size());
        Assert.assertEquals("Unverified isbn number", ISBN, addedBooks.get(0).getIsbn());
        List<BooksDTO> actualBooks = apiHelperBooks.getActualBooks(token, userID);
        Assert.assertEquals("Unverified book number of user", 1, actualBooks.size());
        Assert.assertEquals("Unverified isbn", ISBN, actualBooks.get(0).getIsbn());
    }
}
