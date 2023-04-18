package apiTests;

import api.ApiHelperDemoQA;
import api.EndpointsHomeWorkDemoQA;
import api.dto.DemoQADTO.*;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestsForDemoQAHomeWork {


    private final String USER_NAME_FOR_LOGIN = "Test2107";
    private final String PASSWORD_FOR_LOGIN = "Test2107@";

    Logger logger = Logger.getLogger(getClass());

    ApiHelperDemoQA apiHelper = new ApiHelperDemoQA();


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
        String id = apiHelper.getUserId();
        logger.info(token);
    }


    @Before
    public void deleteBooksStory() {
        given().contentType(ContentType.JSON)
                .log()
                .all()
                .auth().oauth2(apiHelper.getToken())
                .when().delete(EndpointsHomeWorkDemoQA.DELETE_BOOKS, apiHelper.getUserId())
                .then()
                .statusCode(204)
                .log().all();
    }

    @Test
    public void addBookToUser() {

        List<CollectionOfIsbnsDTO> listOfISBN=new ArrayList<>();
        listOfISBN.add(CollectionOfIsbnsDTO.builder()
                .isbn(apiHelper.getIsbnofBooksList(1))
                .build());
        listOfISBN.add(CollectionOfIsbnsDTO.builder()
                .isbn(apiHelper.getIsbnofBooksList(2))
                .build());

        AdditionalBookDTO additionalBookDTO=
                AdditionalBookDTO.builder()
                .userId(apiHelper.getUserId())
                .collectionOfIsbns(listOfISBN)
                .build();

        given().contentType(ContentType.JSON)
                .log()
                .all()
                .auth().oauth2(apiHelper.getToken())
                .body(additionalBookDTO)
                .when().post(EndpointsHomeWorkDemoQA.LIST_OF_BOOKS)
                .then()
                .statusCode(201)
                .log().all();
    }




}
