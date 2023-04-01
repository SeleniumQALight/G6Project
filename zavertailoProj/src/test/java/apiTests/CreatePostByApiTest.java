package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import api.dto.requestDto.CreatePostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deletedAllPostsByUser(){
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByApi() {
        String token = apiHelper.getToken(); //метод має взяти запит, обробити, дістати респонс і повернутит результат стрінг
        logger.info(token);


        CreatePostDTO createPostDTO = CreatePostDTO.builder()
                .title("First Post!")
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

        PostDTO[] actualPost = apiHelper.getAllPostsUser();

        Assert.assertEquals("Number of post", 1, actualPost.length);

        PostDTO[] expectedPostDto = {
                PostDTO.builder()
                        .title(createPostDTO.getTitle())
                        .body(createPostDTO.getBody())
                        .select1(createPostDTO.getSelect1())
                        .uniquePost(createPostDTO.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(ApiHelper.USER_NAME).build())
                        .build()
        };

        SoftAssertions softAssertions = new SoftAssertions(); // перевирка отриманий пост перевиряеться без вказаних параметрив
        softAssertions.assertThat(actualPost)
                .usingRecursiveComparison()
                .ignoringFields("id","createdDate","author.avatar")
                .isEqualTo(expectedPostDto);
        softAssertions.assertAll();

    }
}


