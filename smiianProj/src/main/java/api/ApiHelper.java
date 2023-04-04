package api;

import api.dto.responseDto.PostDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static final String USER_NAME = "testwe";
    private final String  PASSWORD = "Qwerty123456";
    private Logger logger = Logger.getLogger(getClass());  // додаємо до цього классу логер

    RequestSpecification requestSpecification = new RequestSpecBuilder()  // для повторного використання в інших (багатоьх) запитах
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Get token for default user
     * @return
     */
    public String getToken() {                // поліморфізм. Якщо відправлять getToken без параметрів, то спрацює цей getToken
        return getToken(USER_NAME, PASSWORD);
    }

    private String getToken(String userName, String password) {    //  Поліморфізм. getToken з параметрами!
        JSONObject requestParams = new JSONObject();               //  JSONObject - org.json
        requestParams.put("username", userName);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
//                        .contentType(ContentType.JSON)
//                        .log().all()
                        .spec(requestSpecification)   //
                        .body(requestParams.toMap())
                     .when()
                        .post(EndPoints.LOGIN)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();
        return responseBody.asString().replace("\"", "");   // replace робить заміну лапок на пусте місце, щоб респонс був у потрібному вигляді. Без лапок на початку і на кінці.
    }

    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(USER_NAME);
    }

    private PostDTO[] getAllPostsByUser(String userName) {
        return given()
                .spec(requestSpecification)
             .when()
                .get(EndPoints.POST_BY_USER, userName)
             .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(PostDTO[].class);

    }


    public void deletePostsTillPresent() {
        deletePostsTillPresent(USER_NAME, PASSWORD);
    }

    private void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        String token = getToken(userName, password);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title %s was deleted"
                    , listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }

        Assert.assertEquals("Number Of posts", 0, getAllPostsByUser(userName).length);
    }

    private void deletePostById(String token, String id) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", token);

        String response =
                given()
                        .spec(requestSpecification)
                        .body(bodyParams.toMap())
                     .when()
                        .delete(EndPoints.DELETE_POST, id)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        Assert.assertEquals("Message " , "\"Success\"", response);
    }
}
