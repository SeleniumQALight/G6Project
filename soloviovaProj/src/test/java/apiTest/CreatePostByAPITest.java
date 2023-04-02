package apiTest;

import api.APIHelper;
import api.EndPoints;
import api.dto.requestDTO.CreatePostDTO;
import api.dto.responseDTO.AuthorDTO;
import api.dto.responseDTO.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByAPITest {
    APIHelper apiHelper = new APIHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllPostsByUser(){
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByAPI(){
        String token = apiHelper.getToken(); // requesting for token

        CreatePostDTO createPostDTO = CreatePostDTO.builder() // creating the post
                .title("New Post Via API")
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

        PostDTO[] actualPosts = apiHelper.getAllPostsByUser();
        Assert.assertEquals("Number of posts ", 1, actualPosts.length);

        PostDTO[] expectedPostDTO = {
                PostDTO.builder()
                        .title(createPostDTO.getTitle())
                        .body(createPostDTO.getBody())
                        .select1(createPostDTO.getSelect1())
                        .uniquePost(createPostDTO.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(APIHelper.USER_NAME).build())
                        .build()
        };
        SoftAssertions softAssertions = new SoftAssertions();
softAssertions.assertThat(actualPosts)
                .usingRecursiveComparison()
                        .ignoringFields("id","createdDate","author.avatar")
                                .isEqualTo(expectedPostDTO);
        softAssertions.assertAll();
    }
}
