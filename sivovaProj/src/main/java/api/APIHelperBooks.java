package api;

import api.dto.requestDto.AddBookDTO;
import api.dto.responseDto.BookDTO;
import api.dto.responseDto.PostDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIHelperBooks {


    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public Response getResponse(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        Response response =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPointsBooks.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        return response;
    }

    public String getUserId(String userName, String password) {
        Response response= getResponse(userName, password);

        String userId = response.jsonPath().getString("userId").replace("\"","");
        return userId;
    }

    public String getToken(String userName, String password) {
        Response response= getResponse(userName, password);

        String token = response.jsonPath().getString("token").replace("\"","");
        return token;
    }

    private List<BookDTO> getAllBooks() {
       Response response = given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsBooks.BOOKSTORE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

       List<BookDTO> listOfBookDTOs = response.jsonPath().getList("books", BookDTO.class);
       return listOfBookDTOs;
    }
    public String getIsbnOfFirstBook () {
        List<BookDTO> bookDTOS = getAllBooks();
        return bookDTOS.get(0).getIsbn();
    }

    public List<BookDTO> getAllBooksByUser (String userName, String password){
        String userId = getUserId(userName, password);
        String token = getToken(userName,password);

        Response response = given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .when()
                .get(EndPointsBooks.USER_PROFILE, userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        List<BookDTO> listOfBooks = response.jsonPath().getList("books", BookDTO.class);

        System.out.println(listOfBooks);
        return listOfBooks;
    }

    public void deleteBooksByUser(String userName, String password) {
        String userId = getUserId(userName, password);
        String token = getToken(userName,password);

        given()
                .contentType(ContentType.JSON)
                .queryParam("UserId", userId)
                .auth().oauth2(token)
                .when()
                .delete(EndPointsBooks.BOOKSTORE)
                .then()
                .statusCode(204)
                .log().all();

        Assert.assertEquals("Books are not deleted", 0, getAllBooksByUser(userName, password).size());
    }
}
