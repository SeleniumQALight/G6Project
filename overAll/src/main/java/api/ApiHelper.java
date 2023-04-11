package api;

import static io.restassured.RestAssured.given;

import api.dto.responseDto.PostDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

public class ApiHelper {
    public static final String USER_NAME = "qaautotaras";
    private final String PASSWORD = "123456qwerty";
    private Logger logger = Logger.getLogger(getClass());


    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Get Token for default user
     * @return
     */
    public String getToken() {
        return getToken(USER_NAME, PASSWORD);
    }

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
//                        .contentType(ContentType.JSON)
//                        .log().all()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();
        return responseBody.asString().replace("\"", "");
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


    public void createPost(String title, String userName, String password) {
        String token = getToken(userName.toLowerCase(), password);

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("title", title);
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("uniquePost", "no");
        requestParams.put("token", token);

        given()
                .spec(requestSpecification)
                .body(requestParams)
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200);


    }


}
