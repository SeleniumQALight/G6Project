package apiTest;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPostsByUserTest(){
         PostDTO[] responseAsDto =   given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(EndPoints.POST_BY_USER,USER_NAME)
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .response().as(PostDTO[].class)
            ;
         logger.info("Number of posts = " + responseAsDto.length);
         logger.info("Tile post1 = " + responseAsDto[0].getTitle());
         logger.info("Username of post1 = " + responseAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < responseAsDto.length; i++) {
            Assert.assertEquals("Username doesn't match", USER_NAME, responseAsDto[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
                new PostDTO("test2","test body2", "All Users", "no", new AuthorDTO("autoapi"), false) ,
                new PostDTO("test","test body","All Users","no", new AuthorDTO("autoapi"), false)
        };

        Assert.assertEquals("Numbers of posts doesn't match", expectedResult.length, responseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();

//        for (int i = 0; i < expectedResult.length; i++) {
//            softAssertions.assertThat(responseAsDto[i])
//                    .isEqualToIgnoringGivenFields(expectedResult[i], "id", "createdDate", "author");
//
//            softAssertions.assertThat(responseAsDto[i].getAuthor())
//                    . isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
//        }




        softAssertions.assertThat(responseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedResult);



        softAssertions.assertAll();
    }
}
