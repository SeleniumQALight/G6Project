package apiTests;

import io.restassured.http.ContentType;
import libs.API.ApiHelper;
import libs.API.DTO.responseDTO.AuthorDTO;
import libs.API.DTO.requestDTO.CreatePostDTO;
import libs.API.EndPoints;
import libs.API.DTO.responseDTO.PostDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deleteAllPostsByUser(){
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void CreatePostByApiTest() {
        String token = apiHelper.getToken();

        CreatePostDTO createPostDTO = CreatePostDTO.builder()
                .title("New post from API")
                .body("Post Body")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostDTO)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message ", "\"Congrats.\"", response);

        PostDTO[] actualPosts = apiHelper.getAllPostsByUser();

        Assert.assertEquals("Number of posts ", 1, actualPosts.length);

        PostDTO[] expectedPostDTO = {
                PostDTO.builder()
                        .title(createPostDTO.getTitle())
                        .body(createPostDTO.getBody())
                        .select1(createPostDTO.getSelect1())
                        .uniquePost(createPostDTO.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(ApiHelper.USER_NAME).build())
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
