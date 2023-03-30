package apiTest;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class CreatePostByApiTest {
    ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllPostsByUser() {
        apiHelper.deletePostTillPresent();
    }

    @Test
    public void createPostByApi() {
        String token = apiHelper.getToken();

        CreatePostDto createPostDto = CreatePostDto.builder()
                .title("New Post from API")
                .body("Post Body")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(createPostDto)
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();

        assertEquals("Message ", "\"Congrats.\"", response);

        PostDTO[] actualPosts = apiHelper.getAllPostsByUser();
        assertEquals("Number of posts ", 1, actualPosts.length);
        PostDTO[] expectedPostDto = {PostDTO.builder()
                .title(createPostDto.getTitle())
                .body(createPostDto.getBody())
                .select1(createPostDto.getSelect1())
                .uniquePost(createPostDto.getUniquePost())
                .isVisitorOwner(false)
                .author(AuthorDTO.builder().username(ApiHelper.USER_NAME).build())
                .build()
        };
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPosts)
                .usingRecursiveComparison()
                .ignoringFields("id","createdDate","author.avatar")
                .isEqualTo(expectedPostDto);
        softAssertions.assertAll();
    }
}
