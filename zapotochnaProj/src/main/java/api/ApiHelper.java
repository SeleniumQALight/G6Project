package api;

import api.dto.responseDto.PostDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelper {          //в цей клас виносимо методи, які можуть щось робити
    // різні апішки на токен, корзина, створення юзера...і т.д.
    public static final String USER_NAME = "wnqcsgwuourgowyobu";
    private final String PASSWORD = "wnqcsgwuourgowyobu@tmmbt.com";

    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder() //це можливість об'єднати якісь частини налаштування для запиту
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * Get Token for default user
     *
     * @return
     */

    public String getToken() {  //створили знизу ще один метод .
        return getToken(USER_NAME, PASSWORD);

    }

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password); //"password" ключ з постмана, password - це змінна
        ResponseBody responseBody =
                given()
                        //.contentType(ContentType.JSON)
                        //.log().all()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        return responseBody.asString().replace("\"", "");
    }

    public PostDTO[] getAllPostsByUser() { //це для дефолтного юзера
        return getAllPostsByUser(USER_NAME);
    }

    private PostDTO[] getAllPostsByUser(String userName) {

        return given()
                .spec(requestSpecification) //це можливість об'єднати якісь частини налаштування для запиту
                .when()
                .get(EndPoints.POST_BY_USER, userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(PostDTO[].class);
    }

    public void deletePostsTillPresent() {
        deletePostsTillPresent(USER_NAME, PASSWORD);
    }

    private void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listsOfPosts = getAllPostsByUser(userName); //отримаємо всі пости для нашого юзера
        String token = getToken(userName,password);

        for (int i = 0; i <listsOfPosts.length ; i++) {
            deletePostById(token, listsOfPosts[i].getId());
            logger.info(String.format("Post with id %s  and title %s was deleted", listsOfPosts[i].getId(), listsOfPosts[i].getTitle()));


        }
        Assert.assertEquals("number of posts " , 0 , getAllPostsByUser(userName).length);

    }

    private void deletePostById(String token, String id) {
        JSONObject bodyParams = new JSONObject(); //в боді відправляємо токен
        bodyParams.put("token", token);

        String response =
                given()
                        .spec(requestSpecification)
                        .body(bodyParams.toMap())
                        .when()
                        .delete(EndPoints.DELETE_POST, id) //тип запиту
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message ", "\"Success\"", response);

    }
}
