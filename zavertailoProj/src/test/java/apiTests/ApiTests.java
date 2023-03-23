package apiTests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger lodder = Logger.getLogger(getClass());
    @Test
    public void getPostsByUserTest(){
       PostDTO[] responseAsDto = given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
            .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class)
        ;
       lodder.info("Number of posts = " + responseAsDto.length);
       lodder.info("Title post1 = " + responseAsDto[0].getTitle()  );
       lodder.info("User name post1 = " + responseAsDto[0].getAuthor().getUsername());

       for (int i = 0; i < responseAsDto.length; i++) {
            Assert.assertEquals("UserName is not matched in post " + i
                    ,USER_NAME, responseAsDto[i].getAuthor().getUsername());
        }

       PostDTO[] expectedResult = {
//               new PostDTO("test2","test body2", "All Users", "no", new AuthorDTO("autoapi"), false) ,
//               new PostDTO("test","test body","All Users","no", new AuthorDTO("autoapi"), false)
               PostDTO.builder().title("test2").body("test body2").select1("All Users").uniquePost("no")
                       .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                       .build(),
               PostDTO.builder().title("test").body("test body").select1("All Users").uniquePost("no")
                       .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                       .build()
       };
       Assert.assertEquals("Number of posts", expectedResult.length, responseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();

//        for (int i = 0; i < expectedResult.length; i++) {
//            softAssertions.assertThat(responseAsDto[i])
//                    .isEqualToIgnoringGivenFields(expectedResult[i], "id", "createdDate", "author");
//            softAssertions.assertThat(responseAsDto[i].getAuthor())
//                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
//
//        }
//
        softAssertions.assertThat(responseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "isVisitorOwner", "author.avatar")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();

    }

    @Test
    public  void getAllPostByUserNegative(){
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

        org.junit.Assert.assertEquals("Massage in response", "\"Sorry, invalid user requested.undefined\"", actualResponse);
        org.junit.Assert.assertEquals("Massage in response", "Sorry, invalid user requested.undefined", actualResponse
                .replace("\"", ""));

    }

    @Test
    public void  getAllPostByUserPath(){
        Response actualResponse =
                given()
                        .contentType(ContentType.JSON)  // зберегти повний запит
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        List<String> actualTitleList = actualResponse.jsonPath().getList("title", String.class); // дстать тайтл по всем постам
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertions.assertThat(actualTitleList.get(i)).as("Item number" + i).contains("test");

        }
        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class); // в доп вложености проверить текст
        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertions.assertThat(actualAuthorList.get(i).get("username"))
                    .as("Item number" + i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll(); //вивести все что не совпало при этом пройтись по всем а не упасть на первой ошибке

    }

    @Test
    public void getAllPostByUserSchema(){
        given()
                .contentType(ContentType.JSON)  // зберегти повний запит
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));

    }


}
