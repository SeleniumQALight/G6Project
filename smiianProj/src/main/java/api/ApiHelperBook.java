package api;

import api.dto.requestDto.AddBookReqHwTwoDemoqaDTO;
import api.dto.requestDto.IsbnReqHwTwoDemoqaDTO;
import api.dto.requestDto.LoginReqHwTwoDemoqaDTO;
import api.dto.responseDto.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ApiHelperBook {


    Logger logger = Logger.getLogger(getClass());


    RequestSpecification requestSpecification = new RequestSpecBuilder()  // для повторного використання в інших (багатоьх) запитах
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public LoginRespHwTwoDTO loginByUser() {

        LoginReqHwTwoDemoqaDTO loginDemoqaDTO =            // Створюємо боді-файл (loginDemoqaDTO) для запита POST, що буде далі
                LoginReqHwTwoDemoqaDTO.builder()           // "LoginReqHwTwoDemoqaDTO" це тип даних. DTO з такою назвою попередньо створюється
                        .userName("testA")                 //
                        .password("qQwerty)(123456&")      //
                        .build();                          //

        LoginRespHwTwoDTO respLoginDTO =
                given()
                        .spec(requestSpecification)
                        .body(loginDemoqaDTO)              // вставляємо body який створювали вище (loginDemoqaDTO)
                     .when()
                        .post(EndPointsDemoqa.LOGIN)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(LoginRespHwTwoDTO.class);
        return respLoginDTO;
    }


    public void deleteAllBooksById(String userId, String token) {

        String respDeleteAllBooks =
                given()
                        .spec(requestSpecification)
                        .auth().oauth2(token)
                     .when()
                        .delete(EndPointsDemoqa.DELETE_ALL_BOOKS, userId)
                     .then()
                        .statusCode(204)
                        .log().all()
                        .extract().response().getBody().asString();
    }


    public GetUsersBooksRespHwTwoDTO getAllBooks(String token) {
        GetUsersBooksRespHwTwoDTO respGetAllBooksDTO =
                given()
                        .spec(requestSpecification)
                        .auth().oauth2(token)
                     .when()
                        .get(EndPointsDemoqa.GET_ALL_BOOKS)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(GetUsersBooksRespHwTwoDTO.class);

        return respGetAllBooksDTO;
    }


    public void addBooksToUser(String token, String userId, String isbn) {
        List<IsbnReqHwTwoDemoqaDTO> listOfUsersBooks = new ArrayList<>();
        listOfUsersBooks.add(IsbnReqHwTwoDemoqaDTO.builder().isbn(isbn).build());


        AddBookReqHwTwoDemoqaDTO addBookDTO = AddBookReqHwTwoDemoqaDTO.builder() // створюємо DTO-шку для body
                .userId(userId)
                .collectionOfIsbns(listOfUsersBooks)
                .build();

                given()
                        .spec(requestSpecification)
                        .auth().oauth2(token)
                        .body(addBookDTO)
                     .when()
                        .post(EndPointsDemoqa.ADD_BOOK_TO_USER)
                     .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response().getBody().asString();


    }


    public GetAllBooksRespHwTwoDemoqaDTO getUsersBooks(String token, String userId) {
        GetAllBooksRespHwTwoDemoqaDTO respGetUsersBooksDTO =
                given()
                        .spec(requestSpecification)
                        .auth().oauth2(token)
                     .when()
                        .get(EndPointsDemoqa.GET_USER_INFO, userId)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(GetAllBooksRespHwTwoDemoqaDTO.class);

        logger.info(respGetUsersBooksDTO);
        return respGetUsersBooksDTO;
    }

}
