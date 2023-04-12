package apiTests;

import api.ApiHelperForBookStore;
import api.EndPointsBookStore;
import api.dto.bookStoreDTO.CollectionOfBookDTO;
import api.dto.bookStoreDTO.RequestAddBookDTO;
import api.dto.bookStoreDTO.ResponseBookStoreDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookStoreTest {
    ApiHelperForBookStore apiHelperForBookStore = new ApiHelperForBookStore();
    Logger logger = Logger.getLogger(getClass());

    public static final String USER_NAME = "BookTest";
    public static final String PASSWORD = "Qwerty12!@";

    @Before
    public void deleteBooksForUser() {
        apiHelperForBookStore.deleteUserBooks(USER_NAME,PASSWORD);
    }

    @Test
    public void checkAddBook(){
        String userId = apiHelperForBookStore.getUserId(USER_NAME,PASSWORD);
        String token = apiHelperForBookStore.getToken(USER_NAME,PASSWORD);
        String isbn = apiHelperForBookStore.getBookIsbn();

        List<CollectionOfBookDTO>  collectionOfBookDTO = List.of(CollectionOfBookDTO.builder()
                .isbn(isbn)
                .build());

        RequestAddBookDTO addBookDTO = RequestAddBookDTO.builder()
                .userId(userId)
                .collectionOfIsbns(collectionOfBookDTO)
                .build();

        ResponseBody responseBody =  given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .body(addBookDTO)
                .when()
                .post(EndPointsBookStore.BOOKSTORE)
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        List<ResponseBookStoreDTO> responseBookStoreDTOList = responseBody.jsonPath().getList("books", ResponseBookStoreDTO.class);

        Assert.assertEquals("Not one book", 1, responseBookStoreDTOList.size());
        Assert.assertEquals("Isbn doesn't match", isbn, responseBookStoreDTOList.get(0).getIsbn());

    }

}
