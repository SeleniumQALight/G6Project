package apiTests;

import api.BooksStoreApi;
import api.BooksStoreApiHelper;
import api.BooksStoreDtos.*;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BooksStoreApiTests {
    private BooksStoreApiHelper apiHelper = new BooksStoreApiHelper();


    @Test
    public void testThatUserIsAbleToAddBooksToUserProfileViaApi() {
        apiHelper.deleteAllBooksForUser();

        List<Book> allBooks = apiHelper.getAllBooksInStore();

        CollectionOfIsbn isbn = CollectionOfIsbn.builder()
                .isbn(allBooks.get(0).getIsbn())
                .build();

        AddBooksRequestDTO booksForAdding = AddBooksRequestDTO.builder()
                .userId(apiHelper.getUserId())
                .collectionOfIsbns(List.of(isbn))
                .build();

        AddBooksResponseDTO addedBooks = given().auth()
                .oauth2(apiHelper.getToken())
                .contentType(ContentType.JSON)
                .log().all()
                .body(booksForAdding)
                .when()
                .post(BooksStoreApi.BOOKS)
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response()
                .as(AddBooksResponseDTO.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(booksForAdding.getCollectionOfIsbns())
                        .usingRecursiveComparison()
                                .isEqualTo(addedBooks.getCollectionOfIsbns());
        softAssertions.assertAll();

        List<Book> books = apiHelper.getUserProfile().getBooks();

        Assert.assertEquals(books.size(),booksForAdding.getCollectionOfIsbns().size());

        Assert.assertEquals(books.get(0).getIsbn(),booksForAdding.getCollectionOfIsbns().get(0).getIsbn());
    }
}
