package api;

import api.dto.responseDto.PostDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static final String USER_NAME = "kovatest";
    private final String USER_PASSWORD = "kovatest123456";
    Logger logger = Logger.getLogger(getClass());

    public String getToken() {
        return getToken(USER_NAME, USER_PASSWORD);
    }

    public String getToken(String login, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", login);
        requestBody.put("password", password);

        ResponseBody body = given()
                //.contentType(ContentType.JSON)
                //.log().all()
                .spec(getRequestSpecification())
                .body(requestBody.toMap())
                .post(Endpoints.LOGIN)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody();

        return body.asString().replace("\"", "");
    }

    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(USER_NAME);
    }


    public void deleteAllPostsTillPresent() {
        deleteAllPostsTillPresent(USER_NAME, USER_PASSWORD);
    }

    private void deleteAllPostsTillPresent(String userName, String password){
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        String token = getToken(USER_NAME, USER_PASSWORD);

        for (int i = 0; i < listOfPosts.length ; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title %s was deleted",
                    listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }

        Assert.assertEquals("Number of posts", 0, getAllPostsByUser(userName).length);

    }
    private void deletePostById(String token, String id) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", token);

        String response =
                given()
                        .spec(getRequestSpecification())
                        .body(bodyParams.toMap())
                        .when()
                        .delete(Endpoints.DELETE_POST, id)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message", "\"Success\"", response);
    }


    private PostDTO[] getAllPostsByUser(String userName) {
        return given().spec(getRequestSpecification())
                .when()
                .get(Endpoints.POST_BY_USER, userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response()
                .getBody()
                .as(PostDTO[].class);

    }

    private RequestSpecification getRequestSpecification() {
        //   return given().contentType(ContentType.JSON);
        return new RequestSpecBuilder().setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }


}
