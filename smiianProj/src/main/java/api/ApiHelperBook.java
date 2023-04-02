package api;

import api.dto.requestDto.LoginReqHwTwoDemoqaDTO;
import api.dto.responseDto.LoginRespHwTwoDTO;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ApiHelperBook {


    public LoginRespHwTwoDTO loginByUser() {

        LoginReqHwTwoDemoqaDTO loginDemoqaDTO =
                LoginReqHwTwoDemoqaDTO.builder()
                        .userName("testA")
                        .password("qQwerty)(123456&")
                        .build();

        LoginRespHwTwoDTO respLoginDTO =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(loginDemoqaDTO)
                        .when()
                        .post(EndPointsDemoqa.LOGIN_DEMOQA)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(LoginRespHwTwoDTO.class);

        return respLoginDTO;
    }
}
