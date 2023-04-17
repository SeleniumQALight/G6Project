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

public class ApiHelper {

   public static final  String USER_Name ="kamal";
    private final String PASSWORD = "Test12345678";

    private Logger logger=Logger.getLogger(getClass());

    RequestSpecification requestSpecification=new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();

    /*
    Get token for default user
    @return
     */
    public String getToken() {

        return getToken(USER_Name, PASSWORD);
    }

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);


        ResponseBody responseBody =
                given()
                        //.contentType(ContentType.JSON)
                       // .log().all()
                        .spec(requestSpecification)
                        .body(requestParams.toMap()).when()
                        .post(Endpoints.LOGIN).then().statusCode(200)
                        .log().all().extract().response().getBody();

        return responseBody.asString().replace("\"", "");
    }

    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(USER_Name);
    }


    private PostDTO[] getAllPostsByUser(String userName) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(Endpoints.POST_BY_USER, userName)
                .then().statusCode(200).log().all()
                .extract().response().getBody().as(PostDTO[].class);
    }



    public void deletePostsTillPresent() {
        deletePostsTillPresent(USER_Name, PASSWORD);
    }


    public void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listOfPosts=getAllPostsByUser(userName);
        String token=getToken(userName, password);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token,listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title %s was deleted",
                    listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }

        Assert.assertEquals("Number of posts", 0, getAllPostsByUser().length);
    }




    private void deletePostById(String token, String id) {
        JSONObject bodyParams=new JSONObject();
        bodyParams.put("token", token);

        String response=given()
                .spec(requestSpecification).body(bodyParams.toMap())

                .when()
                .delete(Endpoints.DELETE_POST, id)
                .then().statusCode(200).log().all().extract().response().getBody().asString();

        Assert.assertEquals("","\"Success\"" , response);

    }


    public void createPost(String userName, String password, String title) {
        String token=getToken(userName.toLowerCase(),password);

        CreatePostDTO createPostDTO= CreatePostDTO.builder()
                .title(title)
                .body("PostBody")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String response=given().contentType(ContentType.JSON)
                .log().all()
                .body(createPostDTO)
                .when()
                .post(Endpoints.CREATE_POST)
                .then().statusCode(200)
                .log().all()
                .extract().response().getBody().asString();

        Assert.assertEquals("","\"Congrats.\"", response);


    }
}
