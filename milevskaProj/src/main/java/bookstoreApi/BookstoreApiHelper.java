package bookstoreApi;

import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;


public class BookstoreApiHelper {
    private final String USER_NAME = "goshia";
    private final String PASSWORD = "Qwe1234!";

    public String getToken(){
        return getToken(USER_NAME, PASSWORD);
    }

    public String getToken(String userName, String password) {
        JSONObject requestParameters = new JSONObject();
        requestParameters.put("username", userName);
        requestParameters.put("password", password);

        String responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParameters.toMap())
                        .post(EndpointBookstore.login)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().jsonPath().get("token");
        return responseBody;
    }
    public String getUserID(){
        return getToken(USER_NAME, PASSWORD);
    }

    public String getUserID(String userName, String password) {
        JSONObject requestParameters = new JSONObject();
        requestParameters.put("username", userName);
        requestParameters.put("password", password);

        String responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParameters.toMap())
                        .post(EndpointBookstore.login)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().jsonPath().get("userId");
        return responseBody;
    }

}
