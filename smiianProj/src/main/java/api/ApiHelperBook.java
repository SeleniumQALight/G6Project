package api;

import api.dto.requestDto.LoginReqHwTwoDemoqaDTO;
import api.dto.responseDto.GetAllBooksRefHwTwoDTO;
import api.dto.responseDto.LoginRespHwTwoDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ApiHelperBook {


    Logger logger = Logger.getLogger(getClass());


    public LoginRespHwTwoDTO loginByUser() {

        RequestSpecification requestSpecification = new RequestSpecBuilder()  // для повторного використання в інших (багатоьх) запитах
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();




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


    private void deleteAllBookById(String userId, String token) {

        String respDeleteAllBooks =
                given()
//                        .contentType(ContentType.JSON)
//                        .log().all()
                        .spec(requestSpecification)
                        .auth().oauth2(token)
                     .when()
                        .delete(EndPointsDemoqa.DELETE_ALL_BOOKS, userId)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
    }

    public GetAllBooksRefHwTwoDTO[] getAllBooks(String token) {
        GetAllBooksRefHwTwoDTO[] respGetAllBooksDTO =
                given()
                        .spec(requestSpecification)
                        .auth().oauth2(token)
                     .when()
                        .get(EndPointsDemoqa.GET_ALL_BOOKS)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(GetAllBooksRefHwTwoDTO[].class);

        logger.info(respGetAllBooksDTO[0].getIsbn());
        return respGetAllBooksDTO;
    }

}
