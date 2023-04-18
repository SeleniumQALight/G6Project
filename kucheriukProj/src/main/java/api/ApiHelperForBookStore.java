package api;

import api.dto.bookStoreDTO.ResponseBookStoreDTO;
import api.dto.bookStoreDTO.UserLoginDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperForBookStore {


    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public UserLoginDTO responseLogin(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams.toMap())
                .when()
                .post(EndPointsBookStore.LOGIN)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(UserLoginDTO.class);
    }

    public List<ResponseBookStoreDTO> getAllBooks(){
        ResponseBody responseBody = given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsBookStore.BOOKSTORE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        List<ResponseBookStoreDTO> listOfResponseBookStoreDTO = responseBody.jsonPath().getList("books", ResponseBookStoreDTO.class);
        return listOfResponseBookStoreDTO;
    }

    public String getIsbnOfFirstBook(){
        List<ResponseBookStoreDTO> bookStoreDTO = getAllBooks();
        return bookStoreDTO.get(0).getIsbn();
    }

    public void deleteUserBooks(String userId, String token){

        given()
                .contentType(ContentType.JSON)
                .queryParam("UserId", userId)
                .auth().oauth2(token)
                .when()
                .delete(EndPointsBookStore.BOOKSTORE)
                .then()
                .statusCode(204)
                .log().all();
        Assert.assertEquals("Books still displayed", 0, getBooksUsers(userId, token).size());
    }

    public List<ResponseBookStoreDTO> getBooksUsers(String userId, String token){

        ResponseBody responseBody = given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .when()
                .get(EndPointsBookStore.USER_FILE, userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        List<ResponseBookStoreDTO> listOfResponseBookStore = responseBody.jsonPath().getList("books", ResponseBookStoreDTO.class);
        return listOfResponseBookStore;
    }
}
