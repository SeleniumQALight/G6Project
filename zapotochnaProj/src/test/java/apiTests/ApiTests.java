package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given; //стандарт для автоматизації роботи з апі

public class ApiTests {  //тут не наслідуємся з BaseTest тут нема вебдрайверу, локаторів.

    final String USER_NAME = "autoapi";
    @Test
    public void getPostsByUserTest(){

        given() // pre-cond
                .contentType(ContentType.JSON) //тут вказуємо хедери які нам потрібні
                .log().all()
                .when() //action
                .get(EndPoints.POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                ;

    }


}
