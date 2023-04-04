package api;

import api.dto.requestDto.LoginReqHwTwoDemoqaDTO;
import api.dto.responseDto.LoginRespHwTwoDTO;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ApiHelperBook {


    public LoginRespHwTwoDTO loginByUser() {

        LoginReqHwTwoDemoqaDTO loginDemoqaDTO =            // Створюємо боді-файл (loginDemoqaDTO) для запита POST, що буде далі
                LoginReqHwTwoDemoqaDTO.builder()           // "LoginReqHwTwoDemoqaDTO" це тип даних. DTO з такою назвою попередньо створюється
                        .userName("testA")                 //
                        .password("qQwerty)(123456&")      //
                        .build();                          //

        LoginRespHwTwoDTO respLoginDTO =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(loginDemoqaDTO)              // вставляємо body який створювали вище (loginDemoqaDTO)
                     .when()
                        .post(EndPointsDemoqa.LOGIN)
                     .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(LoginRespHwTwoDTO.class);

        return respLoginDTO;
    }

//    public LoginRespHwTwoDTO loginByUser() {
//
//        LoginReqHwTwoDemoqaDTO loginDemoqaDTO =
//                LoginReqHwTwoDemoqaDTO.builder()
//                        .userName("testA")
//                        .password("qQwerty)(123456&")
//                        .build();


}
