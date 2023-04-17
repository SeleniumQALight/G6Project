package api;

import api.dto.requestDTO.CreatePostDTO;
import api.dto.responseDTO.PostDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class APIHelper {
    public static final String USER_NAME = "yuliiasolov";
    final String PASSWORD = "Filimonova2012";
    private Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Get token for default user
     *
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

    public void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        String token = getToken(userName, password);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePOSTbyID(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title %s is deleted", listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }
        Assert.assertEquals(" Number of posts ", 0, getAllPostsByUser(userName).length);
    }

    private void deletePOSTbyID(String token, String id) {
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

        Assert.assertEquals("Message ", "\"Success\"", response);
    }

    public void createPost(String userName, String password, String title) {
        String token = getToken(userName.toLowerCase(), password);
        CreatePostDTO createPostDTO = CreatePostDTO.builder() // creating the post
                .title(title)
                .body("Post Body Via API")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String response = given()// posting the post to the website
                .contentType(ContentType.JSON)
                .log().all()
                .body(createPostDTO)
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();

        Assert.assertEquals("There is no post created ", "\"Congrats.\"", response);
    }
}
