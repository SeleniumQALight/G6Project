package apiTests;

import api.AuthorDTO;
import api.Endpoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class apiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPostsByUserTest() {
        PostDTO[] responseAsDTO = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);

        logger.info("Number of Posts= " + responseAsDTO.length);
        logger.info("Title post1 = " + responseAsDTO[0].getTitle());
        logger.info("Username post1 = " + responseAsDTO[0].getAuthor().getUsername());

        //получаем юзернейм в каждом посте
        for (int i = 0; i < responseAsDTO.length; i++) {
            Assert.assertEquals("Username is not matched" + i, USER_NAME, responseAsDTO[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
                //2 урок
                //    new PostDTO("test2","test body2", "All Users", "no", new AuthorDTO("autoapi"), false) ,
                //    new PostDTO("test","test body","All Users","no", new AuthorDTO("autoapi"), false)
                //3 урок. Пишем новый код с помощью lombok (аналогия закомментированного кода)
                PostDTO.builder()
                        .title("test2").body("test body2").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build())
                        .isVisitorOwner(false)
                        .build(),
                PostDTO.builder()
                        .title("test").body("test body").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build())
                        .isVisitorOwner(false)
                        .build()


        };

        Assert.assertEquals("Number of posts ", expectedResult.length, responseAsDTO.length);


        SoftAssertions softAssertions = new SoftAssertions();

        //2 урок
       /*for (int i=0; i<expectedResult.length; i++){
            softAssertions.assertThat(responseAsDTO[i]).isEqualToIgnoringGivenFields(expectedResult[i],"id",
                    "createdDate", "author");

            softAssertions.assertThat(responseAsDTO[i].getAuthor()).isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(),"avatar");
        }*/


        //3 урок. аналогия закомментированного кода
        softAssertions.assertThat(responseAsDTO)
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
                        .get(Endpoints.POST_BY_USER, "notValidUser")
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message in response ", "Sorry, invalid user requested.undefined", actualResponse
                .replace("\"",""));

    }



    @Test
    public void getAllPostsByUserPath(){
        Response actualResponse=
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(Endpoints.POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        List<String> actualTitleList=actualResponse.jsonPath().getList("title",String.class);

        SoftAssertions softAssertions=new SoftAssertions();
        for (int i=0; i<actualTitleList.size(); i++){
            softAssertions.assertThat(actualTitleList.get(i)).as("Iten number "+i).contains("test");
        }



        List<Map> actualAuthorList=actualResponse.jsonPath().getList("author",Map.class);

        for (int i=0; i<actualAuthorList.size(); i++){
            softAssertions.assertThat(actualAuthorList.get(i).get("username"))
                    .as("Iten number "+i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();

    }


    @Test
    public void getAllPostsByUserSchema(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all().body(matchesJsonSchemaInClasspath("response.json"))
                ;

    }


}
