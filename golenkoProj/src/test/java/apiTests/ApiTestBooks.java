package apiTests;

import api.ApiHelperBooks;
import api.EndPoints;
import api.EndPointsBooks;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestBooks {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteBooksForUser(){
        String token = apiHelperBooks.getToken();
        String userID = apiHelperBooks.getUserID();
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .when()
                .delete(EndPointsBooks.DELETE_BOOKS, userID)
                .then()
                .statusCode(204)
                .log().all();
    }


    @Test
    public void checkBookStore(){
        String token = apiHelperBooks.getToken();
        String userID = apiHelperBooks.getUserID();



    }

}
