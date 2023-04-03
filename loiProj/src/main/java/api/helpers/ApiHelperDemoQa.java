package api.helpers;

import api.Endpoinds;
import api.dto.responseDTO.LoginDTO;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoQa {
    private final String USER_NAME = "UserForApiTest";
    private final String PASSWORD = "UserForApiTest123456!";

    public String getUserId(){
        return getUserId(USER_NAME, PASSWORD);
    }

    public String getToken(){
        return getToken(USER_NAME,PASSWORD);
    }

    public String getUserId(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        LoginDTO responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParams.toMap())
                        .when()
                        .post(Endpoinds.DEMO_QA_LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .as(LoginDTO.class);
        return responseBody.getUserId();
    }

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", userName);
        requestParams.put("password", password);

        LoginDTO responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParams.toMap())
                        .when()
                        .post(Endpoinds.DEMO_QA_LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .as(LoginDTO.class);
        return responseBody.getToken();
    }

}
