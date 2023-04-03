package apiTests;

import api.dto.responseDto.AuthorDTO;
import api.EndPoints;
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

public class ApiTests {  //тут не наслідуємся з BaseTest тут нема вебдрайверу, локаторів.

    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPostsByUserTest() {
        PostDTO[] responseAsDto = given() // pre-cond
                .filter(new AllureRestAssured()) // це для респонсів алюру
                .contentType(ContentType.JSON) //тут вказуємо хедери які нам потрібні
                .log().all()
                .when() //action
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);


        logger.info("Number of posts  = " + responseAsDto.length);
        logger.info("Title post1 = " + responseAsDto[0].getTitle());
        logger.info("Username post1 " + responseAsDto[0].getAuthor().getUsername());


        for (int i = 0; i < responseAsDto.length; i++) {
            Assert.assertEquals("UserName is not matched ",
                    USER_NAME, responseAsDto[i].getAuthor().getUsername());


        }

        PostDTO[] expectedResult = {
//                new PostDTO("test2", "test body2", "All Users", "no", new AuthorDTO("autoapi"), false),
//                new PostDTO("test", "test body", "All Users", "no", new AuthorDTO("autoapi"), false)
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
//
//            softAssertions.assertThat(responseAsDto[i])
//                    .isEqualToIgnoringGivenFields(expectedResult[i], "id", "createdDate", "author");
//            softAssertions.assertThat(responseAsDto[i].getAuthor())
//                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
//
//        }


        softAssertions.assertThat(responseAsDto)  //responseAsDto - actual result from api
                .usingRecursiveComparison()  //означає що потрібно проходити по всім ітераціям
                .ignoringFields("id", "createdDate", "isVisitorOwner", "author.avatar")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();

    }


    @Test
    public void getAllPostsByUserNegative() {
        String actualResponse =
                given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER, "notValidUser")
                        .then()
                        .statusCode(200) //тут ставимо те що очікуємо 200, 500...
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message is response", "\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message is response", "Sorry, invalid user requested.undefined", actualResponse
                .replace("\"", ""));

    }

    @Test
    public void getAllPostsByUserPath() {

        Response actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .filter(new AllureRestAssured())
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();


        List<String> actualTitleList = actualResponse.jsonPath().getList("title", String.class);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertions.assertThat(actualTitleList.get(i)).as("Item number " + i).contains("test");
        }

        List<Map> actualAutorList = actualResponse.jsonPath().getList("author", Map.class);
        for (int i = 0; i < actualAutorList.size(); i++) {
            softAssertions.assertThat(actualAutorList.get(i).get("username"))
                    .as("Item number " + i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();


    }
    @Test
    public void getAllPostsByUsersSchema() {
        given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"))
        ;


    }

}
