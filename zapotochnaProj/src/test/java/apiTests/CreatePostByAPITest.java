package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDTO;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByAPITest {

    ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllPostsByUser(){
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByApi() {


        String token = apiHelper.getToken(); //метод має взяти запитт, обробити, дістати респонс і повернутит результат стрінг
        logger.info(token);

        CreatePostDTO createPostDTO = CreatePostDTO.builder()    // ми створюємо об'єкт який створенний в дто
                .title("new post from api")
                .body("post text in body")
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

        Assert.assertEquals("Number of posts", 1, actualPosts.length);

        PostDTO[] expectedPostDto = {
                PostDTO.builder()
                        .title(createPostDTO.getTitle())
                        .body(createPostDTO.getBody())
                        .select1(createPostDTO.getSelect1())
                        .uniquePost(createPostDTO.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder()
                                .username(ApiHelper.USER_NAME).build())

                        .build()

        };

        SoftAssertions softAssertions = new SoftAssertions();  //перевірки, що актуал пост буде = експектед. за винятком філдів які ігноримо
        softAssertions.assertThat(actualPosts)
                .usingRecursiveComparison()
                .ignoringFields("id","createdDate","author.avatar")
                .isEqualTo(expectedPostDto);

        softAssertions.assertAll();


    }
}
