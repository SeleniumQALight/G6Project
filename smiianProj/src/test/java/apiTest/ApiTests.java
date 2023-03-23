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

public class ApiTests {
    final String USER_NAME = "autoapi";

    private Logger logger = Logger.getLogger(getClass());   // створюємо Логгер від Apache

    @Test
    public void getPostByUser() {
        PostDTO[] responseAsDTO = given()
                .contentType(ContentType.JSON)
                .log().all()
             .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)     //це і попередні рядки відправить GET на endpoint
             .then()
                .statusCode(200)                        // очікуєм відповідь
                .log().all()
                .extract()
                .response().as(PostDTO[].class)
        ;

        logger.info("Number of posts = " + responseAsDTO.length);
        logger.info("Title post1 =" + responseAsDTO[0].getTitle());
        logger.info("Username post1 = " + responseAsDTO[0].getAuthor().getUsername());

        for (int i = 0; i < responseAsDTO.length; i++) {
            Assert.assertEquals("Username is not matched",
                    USER_NAME, responseAsDTO[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
                new PostDTO("test2","test body2", "All Users", "no", new AuthorDTO("autoapi"), false) ,
                new PostDTO("test","test body","All Users","no", new AuthorDTO("autoapi"), false)
        };

        Assert.assertEquals("Number of posts ", expectedResult.length, responseAsDTO.length);


        SoftAssertions softAssertions = new SoftAssertions();
//        for (int i = 0; i < expectedResult.length; i++) {
//            softAssertions.assertThat(responseAsDTO[i])
//                    .isEqualToIgnoringGivenFields(expectedResult[i], "id", "createdDate", "author");
//            softAssertions.assertThat(responseAsDTO[i].getAuthor())
//                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
//        }

        softAssertions.assertThat(responseAsDTO)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "isVisitorOwner", "author.avatar")
                .isEqualTo(expectedResult[0]);

        softAssertions.assertAll();
    }
}
