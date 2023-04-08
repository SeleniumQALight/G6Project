package apiTests;

import api.dto.responseDto.AuthorDTO;
import api.Endpoints;
import api.dto.responseDto.PostDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {
    private final String USER_NAME = "autoapi";
    public Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPostByUserTest() {
        PostDTO[] responseAsPostDto = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
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
                //  new PostDTO("test2", "test body2", "All Users", "no", new AuthorDTO("autoapi"), false),
                //    new PostDTO("test", "test body", "All Users", "no", new AuthorDTO("autoapi"), false)
                PostDTO.builder()
                        .title("test2")
                        .body("test body2")
                        .author(AuthorDTO.builder().username("autoapi").build())
                        .uniquePost("no")
                        .select1("All Users")
                        .isVisitorOwner(false)
                        .build(),
                PostDTO.builder()
                        .title("test")
                        .body("test body")
                        .author(AuthorDTO.builder().username("autoapi").build())
                        .select1("All Users")
                        .uniquePost("no")
                        .isVisitorOwner(false)
                        .build()
        };

        Assert.assertEquals("Number of posts", expectedRes.length, responseAsPostDto.length);

        SoftAssertions softAssertions = new SoftAssertions();
       /* for (int i = 0; i < expectedRes.length; i++) {

            softAssertions.assertThat(responseAsPostDto[i])
                    .isEqualToIgnoringGivenFields(expectedRes[i], "id", "createdDate", "author");

            softAssertions.assertThat(responseAsPostDto[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedRes[i].getAuthor(), "avatar");
        }*/
        softAssertions.assertThat(responseAsPostDto)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "isVisitorOwner", "author.avatar", "isVisitorOwner")
                .isEqualTo(responseAsPostDto);

        softAssertions.assertAll();
    }


    @Test
    public void getAllPostsNegative() {
        String actualResponse =
                given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(Endpoints.POST_BY_USER, "notValidUser")
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message in response ", "Sorry, invalid user requested.undefined", actualResponse
                .replace("\"", ""));
    }


    @Test
    public void getAllPostByUserPath() {
        Response response =
                given().filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when().get(Endpoints.POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();
        List<String> actualTitleList = response.jsonPath().getList("title", String.class);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertions.assertThat(actualTitleList.get(i)).as("Item number " + i).contains("test");
        }

        List<Map> actualAuthorList = response.jsonPath().getList("author", Map.class);
        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertions.assertThat(actualAuthorList.get(i).get("username"))
                    .as("Item number " + i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();

    }


    @Test
    public void getAllPostsByUserSchema() {
        given() .filter( new AllureRestAssured())
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }
}
