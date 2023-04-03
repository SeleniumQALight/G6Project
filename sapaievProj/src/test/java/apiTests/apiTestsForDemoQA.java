package apiTests;

import api.ApiHelper;
import api.ApiHelperDemoQA;
import api.EndpointsHomeWorkDemoQA;
import api.dto.DemoQADTO.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import libs.Util;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class apiTestsForDemoQA {
    private final String USER_NAME = "kamal_testtt";
    private final String PASSWORD = "E%qwrwrwetger45656445";


    private final String USER_NAME_FOR_LOGIN = "Test2107";
    private final String PASSWORD_FOR_LOGIN = "Test2107@";

    Logger logger = Logger.getLogger(getClass());

    ApiHelperDemoQA apiHelper = new ApiHelperDemoQA();


    @Test
    public void createUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", USER_NAME);
        jsonObject.put("password", PASSWORD);

        given().contentType(ContentType.JSON)
                .log()
                .all()
                .body(jsonObject.toMap())
                .when().post(EndpointsHomeWorkDemoQA.CREATE_USER)
                .then().statusCode(201)
                .log().all();
    }


    @Test
    public void login() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", USER_NAME_FOR_LOGIN);
        jsonObject.put("password", PASSWORD_FOR_LOGIN);

        UserDTO userDTOresponse =
                given().contentType(ContentType.JSON)
                        .log()
                        .all()
                        .body(jsonObject.toMap())
                        .when().post(EndpointsHomeWorkDemoQA.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(UserDTO.class);

        logger.info(userDTOresponse.getUserId());
        logger.info(userDTOresponse.getToken());
        logger.info("-----------------------------------------");
        String token = apiHelper.getToken();
        String id = apiHelper.getId();
        logger.info(token);
    }


    @Test
    public void deleteBooksStory() {
        given().contentType(ContentType.JSON)
                .log()
                .all()
                .auth().oauth2(apiHelper.getToken())
                .when().delete(EndpointsHomeWorkDemoQA.DELETE_BOOKS, apiHelper.getId())
                .then()
                .statusCode(204)
                .log().all();
    }

    @Test
    public void addBookToUser() {
        /*
        BooksDTO books =
                given().contentType(ContentType.JSON)
                        .log()
                        .all()
                        .when().get(EndpointsHomeWorkDemoQA.GET_LIST_OF_BOOKS)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(BooksDTO.class);

        BookDTO [] listOfbook=books.getBooks();

        String numberOfFirstBook=listOfbook[1].getIsbn();

        */

        apiHelper.getIsbnofBooksList(1);

        /*
        AdditionalBookDTO additionalBookDTO=
                AdditionalBookDTO.builder()
                .userId(apiHelper.getId())
                .collectionOfIsbns(CollectionOfIsbnsDTO[].)
                .build();

      */

        JSONObject jsonParams=new JSONObject();
        jsonParams.put("userId",apiHelper.getId());
        jsonParams.put("collectionOfIsbns.isbn",apiHelper.getIsbnofBooksList(1));

        given().contentType(ContentType.JSON)
                .log()
                .all()
                .auth().oauth2(apiHelper.getToken())
                .body(jsonParams.toMap())
                .when().post(EndpointsHomeWorkDemoQA.ADD_BOOK_TO_USER)
                .then()
                .statusCode(200)
                .log().all();


    }




}
