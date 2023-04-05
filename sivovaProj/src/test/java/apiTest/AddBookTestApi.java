package apiTest;

import api.APIHelper;
import api.APIHelperBooks;
import api.EndPoints;
import api.EndPointsBooks;
import api.dto.requestDto.AddBookDTO;
import api.dto.requestDto.CollectionOfIsbnsDTO;
import api.dto.responseDto.BookDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AddBookTestApi {
    APIHelperBooks apiHelper = new APIHelperBooks();
    Logger logger = Logger.getLogger(getClass());
    public static final String USER_NAME = "ApiTestUser";
    private static final String PASSWORD = "Qwerty_123456@";

    @Before
    public void DeleteAllBooksForUser() {
        apiHelper.deleteBooksByUser(USER_NAME,PASSWORD);
    }


    @Test
    public void AddBookForUserByApi(){
        String userId = apiHelper.getUserId(USER_NAME,PASSWORD);
        String token = apiHelper.getToken(USER_NAME,PASSWORD);
        String isbn = apiHelper.getBookISBN();

        List<CollectionOfIsbnsDTO> collectionOfIsbnsDTOList = List.of
                (CollectionOfIsbnsDTO.builder()
                .isbn(isbn)
                .build());

        AddBookDTO addBookDTO = AddBookDTO.builder()
                .userId(userId)
                .collectionOfIsbns(collectionOfIsbnsDTOList)
                .build();

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .auth().oauth2(token)
                        .body(addBookDTO)
                        .when()
                        .post(EndPointsBooks.BOOKSTORE)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response();

        List<BookDTO> responseBooksList = response.jsonPath().getList("books", BookDTO.class);

        Assert.assertEquals("More than one book", 1, responseBooksList.size());

        Assert.assertEquals("isbn doesn't match", isbn, responseBooksList.get(0).getIsbn());

    }

}
