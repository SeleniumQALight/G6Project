package apiTests;

import api.ApiHelper;
import api.Endpoints;
import api.dto.requestDto.CreatePostRequestDTO;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class createPostByApiTest {
    private ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllPosts() {
        apiHelper.deleteAllPostsTillPresent();

    }

    @Test
    public void createPostByApi() {
        String token = apiHelper.getToken();
        logger.info("Current token: " + token);

        CreatePostRequestDTO createPostBody = CreatePostRequestDTO.builder()
                .title("post")
                .body("body")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String response =
                given().contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostBody)
                        .when()
                        .post(Endpoints.CREATE_POST)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response()
                        .getBody()
                        .asString();

        Assert.assertEquals("Message : ", "\"Congrats.\"", response);
        //also need to check that smt is really created need to check via DB
        // in our case we can't connect to DB so we will check  via GET request


        PostDTO[] actualPosts = apiHelper.getAllPostsByUser();
        Assert.assertEquals("Number of pasts", 1, actualPosts.length);

        PostDTO[] expectedPostsDto = {
                PostDTO.builder()
                        .title(createPostBody.getTitle())
                        .body(createPostBody.getBody())
                        .select1(createPostBody.getSelect1())
                        .uniquePost(createPostBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder()
                                .username(ApiHelper.USER_NAME).build())
                        .build()
        };

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPosts)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPostsDto);
        softAssertions.assertAll();

    }
}
