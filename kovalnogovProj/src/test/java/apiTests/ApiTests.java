package apiTests;

import api.AuthorDTO;
import api.Endpoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    private final String USER_NAME = "autoapi";
    public Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPostByUserTest() {
        PostDTO[] responseAsPostDto = given().contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .extract().as(PostDTO[].class);

        logger.info(" number of posts " + responseAsPostDto.length);

        logger.info("Title first Posts" + responseAsPostDto[0].getTitle());

        logger.info("post1 username " + responseAsPostDto[0].getAuthor().getUsername());
        for (int i = 0; i < responseAsPostDto.length; i++) {
            Assert.assertEquals("Name does not match", USER_NAME,
                    responseAsPostDto[i].getAuthor().getUsername());
        }
        PostDTO[] expectedRes = {
                new PostDTO("test2", "test body2", "All Users", "no", new AuthorDTO("autoapi"), false),
                new PostDTO("test", "test body", "All Users", "no", new AuthorDTO("autoapi"), false)
        };

        Assert.assertEquals("Number of posts", expectedRes.length, responseAsPostDto.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedRes.length; i++) {

            softAssertions.assertThat(responseAsPostDto[i])
                    .isEqualToIgnoringGivenFields(expectedRes[i], "id", "createdDate", "author");

            softAssertions.assertThat(responseAsPostDto[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedRes[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();
    }
}
