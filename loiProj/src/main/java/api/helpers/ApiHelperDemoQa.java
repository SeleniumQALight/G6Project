package api.helpers;

import api.Endpoinds;
import api.dto.requestDTO.AddListOfBooksDTO;
import api.dto.responseDTO.LoginDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoQa {
    private final String USER_NAME = "UserForApiTest";
    private final String PASSWORD = "UserForApiTest123456!";

    public String getUserId() {
        return getUserId(USER_NAME, PASSWORD);
    }

    public String getToken() {
        return getToken(USER_NAME, PASSWORD);
    }

    public LoginDTO getLoginResponse(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams.toMap())
                .when()
                .post(Endpoinds.DEMO_QA_LOGIN)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(LoginDTO.class);
    }

    public String getUserId(String userName, String password) {
        return getLoginResponse(userName, password).getUserId();
    }

    public String getToken(String userName, String password) {
        return getLoginResponse(userName, password).getToken();
    }

    public Response getAllListOfBooks() {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoinds.DEMO_QA_BOOK_STORE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
    }

    public Response getAllUserBooks(String userToken, String userId) {
        return given()
                .auth().oauth2(userToken)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoinds.DEMO_QA_USER_ACCOUNT, userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
    }

    public Response addListOfBooksToUser(String userToken, AddListOfBooksDTO bodyRequest) {
        return given()
                .auth().oauth2(userToken)
                .contentType(ContentType.JSON)
                .log().all()
                .body(bodyRequest)
                .when()
                .post(Endpoinds.DEMO_QA_BOOK_STORE)
                .then()
                .statusCode(201)
                .extract().response();
    }

}
