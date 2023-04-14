package api;

import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.PostDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ApiHelper {

    public static final String USER_NAME = "eugensydorenko";
    private final String PASSWORD = "EugenSydorenko1234";
    private Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();

    /*
    Get token for default user
    @return
     */
    public String getToken() {
        return getToken(USER_NAME, PASSWORD);
    }

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);

        ResponseBody responseBody = given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .body(requestParams.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().getBody();

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
                .extract().response()
                .getBody().as(PostDTO[].class);
    }

    public void deletePostTillPresent() {
        deletePostTillPresent(USER_NAME, PASSWORD);
    }

    public void deletePostTillPresent(String userName, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        String token = getToken(userName, password);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostsById(token, listOfPosts[i].getId());
            logger.info(String.format("post with id %s and title %s was deleted", listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }
        assertEquals("Number of posts", 0, getAllPostsByUser(userName).length);
    }

    private void deletePostsById(String token, String id) {
        JSONObject bodyParams = new JSONObject();
        bodyParams.put("token", token);
        String response = given()
                .spec(requestSpecification)
                .body(bodyParams.toMap())
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response()
                .getBody().asString();
        assertEquals("Message ", "\"Success\"", response);

    }
    public void createPost(String username, String password, String title) {
        String token = getToken(username.toLowerCase(), password);

        CreatePostDto createPostDTO = CreatePostDto.builder()
                .title(title)
                .body("Post body")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostDTO)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        assertEquals("Message" , "\"Congrats.\"", response);

    }
}
