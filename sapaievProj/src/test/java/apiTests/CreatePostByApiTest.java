package apiTests;

import api.ApiHelper;
import api.Endpoints;
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

public class CreatePostByApiTest {
    ApiHelper apiHelper=new ApiHelper();
    Logger logger=Logger.getLogger(getClass());


    @Before
    public void deleteAllPostsByUser(){
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByApi(){
       String token=apiHelper.getToken();

        CreatePostDTO createPostDTO= CreatePostDTO.builder()
                .title("New post from API")
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

        PostDTO[] actualPosts=apiHelper.getAllPostsByUser();

        Assert.assertEquals("Number of posts", 1, actualPosts.length);

        PostDTO[] expectedPostDto={
                PostDTO.builder()
                        .title(createPostDTO.getTitle())
                        .body(createPostDTO.getBody())
                        .select1(createPostDTO.getSelect1())
                        .uniquePost(createPostDTO.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(ApiHelper.USER_Name).build())
                        .build()
        };


        SoftAssertions softAssertions=new SoftAssertions();
        softAssertions.assertThat(actualPosts)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                        .isEqualTo(expectedPostDto);

        softAssertions.assertAll();
    }










}
