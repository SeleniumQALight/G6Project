package apiTest;

import api.*;
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
                .response().as(PostDTO[].class);

        logger.info("Number of posts = " + responseAsDTO.length);
        logger.info("Title post1 =" + responseAsDTO[0].getTitle());
        logger.info("Username post1 = " + responseAsDTO[0].getAuthor().getUsername());

        for (int i = 0; i < responseAsDTO.length; i++) {
            Assert.assertEquals("Username is not matched",
                    USER_NAME, responseAsDTO[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
//                new PostDTO("test2","test body2", "All Users", "no", new AuthorDTO("autoapi"), false) ,     //  були потрібні, коли не було анотацій
//                new PostDTO("test","test body","All Users","no", new AuthorDTO("autoapi"), false)
                PostDTO.builder().title("test2").body("test body2").select1("All Users").uniquePost("no")    // білдери очікувань. Сетаєм поля і що в них очікуємо
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
                PostDTO.builder().title("test").body("test body").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build()
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

    @Test
    public void getAllPostsByUser() {
        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()                       // щоб вивести запит в консоль
                        .when()
                        .get(EndPoints.POST_BY_USER, "notValidUser")   // попередні чотири - це відправлення поста
                        .then()
                        .statusCode(200)
                        .log().all()                                // щоб вивести рспонс в консоль
                        .extract().response().getBody().asString()    // дістаємо боді з респонса у вигляді стрінги
                ;
        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message in response ", "Sorry, invalid user requested.undefined", actualResponse.replace("\"", ""));
    }

    @Test
    public void getAllPostsByUsersPath() {
        Response actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        // робимо: пройтися по всіх постах(з респонса) і перевіряємо, що "title" використовує слово "test"

        List<String> actualTitleList = actualResponse.jsonPath().getList("title", String.class);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualTitleList.size(); i++) {                                               //  перша перевірка в Softassertion
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
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));  //  перевіряємо,
        // чи респонс відповідає схемі з папки   main / java / resources


    }

    @Test
    public void homeWorkOne() {
        PrivateHwOneDTO[] responsePrivateHwOneDTO = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.PRIVATE_API_22_03)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivateHwOneDTO[].class);

        logger.info("Number of posts = " + responsePrivateHwOneDTO.length);
//        logger.info("Title post1 =" + responsePrivateHwOneDTO[0].baseCurrency());
//        logger.info("Username post1 = " + responsePrivateHwOneDTO[0].getAuthor().getUsername());

    PrivateHwOneDTO[] expectedResultHwOne = {
            PrivateHwOneDTO.builder().date("22.03.2022").bank("PB").baseCurrency(980).baseCurrencyLit("UAH")
                    .exchangeRate(ExchangeRateDTO.builder().baseCurrency("UAH").currency("AUD").build())
                    .build(),
//            PrivateHwOneDTO.builder().date("111").bank("222").baseCurrency(333).baseCurrencyLit("444")
//                    .exchangeRate(ExchangeRateDTO.builder().baseCurrency("555").currency("666").build())
//                    .build(),
    };

    Assert.assertEquals("Number of posts ", expectedResultHwOne.length, responsePrivateHwOneDTO.length);

    SoftAssertions softAssertionsHwOne = new SoftAssertions();

        softAssertionsHwOne.assertThat(responsePrivateHwOneDTO)
            .usingRecursiveComparison()
            .ignoringFields("saleRateNB","purchaseRateNB")
            .isEqualTo(expectedResultHwOne[0]);

        softAssertionsHwOne.assertAll();
    }
}
