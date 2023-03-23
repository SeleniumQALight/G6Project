package apiTests;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPostsByUserTest() {
        PostDTO[] responseAsDto = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);
        logger.info("Number of posts = " + responseAsDto.length);
        logger.info("Title post1 = " + responseAsDto[0].getTitle());
        logger.info("Username post1 = " + responseAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < responseAsDto.length; i++) {
            Assert.assertEquals("Username is not matched"
                    , USER_NAME, responseAsDto[i].getAuthor().getUsername());
        }
        PostDTO[] expectedResult = {
//        new PostDTO("test2","test body2", "All Users", "no", new AuthorDTO("autoapi"), false) ,
//        new PostDTO("test","test body","All Users","no", new AuthorDTO("autoapi"), false)
                PostDTO.builder().title("test2").body("test body2").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
                PostDTO.builder().title("test").body("test body").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build()

        };

        Assert.assertEquals("Number of posts ", expectedResult.length, responseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();
//        for (int i = 0; i < expectedResult.length; i++) {
//            softAssertions.assertThat(responseAsDto[i])
//                    .isEqualToIgnoringGivenFields(expectedResult[i],"id","createdDate","author");
//            softAssertions.assertThat(responseAsDto[i].getAuthor())
//                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(),"avatar");
//        }
        softAssertions.assertThat(responseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "isVisitorOwner", "author.avatar")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();


    }

    @Test
    public void getAllPostsByUserNegative() {
        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER, "notValidUser")
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();


        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message in response ", "Sorry, invalid user requested.undefined", actualResponse.replace("\"", ""));


    }


}
