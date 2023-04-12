package api;

import api.dto.bookStoreDTO.ResponseBookStoreDTO;
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

public class ApiHelperForBookStore {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public Response response(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        Response response = given()
                .spec(requestSpecification)
                .body(requestParams.toMap())
                .when()
                .post(EndPointsBookStore.LOGIN)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        return response;
    }

    public String getUserId(String userName, String password) {
        Response response = response(userName,password);

        String userId = response.jsonPath().getString("userId").replace("\"","");
        return userId;
    }

    public String getToken(String userName, String password) {
        Response response = response(userName,password);

        String token = response.jsonPath().getString("token").replace("\"","");
        return token;
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

    public String getBookIsbn(){
        List<ResponseBookStoreDTO> bookStoreDTO = getAllBooks();
        return bookStoreDTO.get(0).getIsbn();
    }

    public void deleteUserBooks(String userName, String password){
        String userId = getUserId(userName, password);
        String token = getToken(userName,password);

        given()
                .contentType(ContentType.JSON)
                .queryParam("UserId", userId)
                .auth().oauth2(token)
                .when()
                .delete(EndPointsBookStore.DELETE_BOOKS)
                .then()
                .statusCode(204)
                .log().all();
        Assert.assertEquals("Books still displayed", 0, getBooksUsers(userName, password).size());
    }

    public List<ResponseBookStoreDTO> getBooksUsers(String userName, String password){
        String userId = getUserId(userName, password);
        String token = getToken(userName,password);

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
