
package apiTest;

import api.ApiHelper;
import api.EndPoints;
import api.EndPointsDemoqa;
import api.dto.requestDto.CreatePostDTO;
import api.dto.requestDto.LoginReqHwTwoDemoqaDTO;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.LoginRespHwTwoDTO;
import api.dto.responseDto.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BookOrderTest {

    ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void loginDemoqa() {
        LoginReqHwTwoDemoqaDTO loginDemoqaDTO = LoginReqHwTwoDemoqaDTO.builder()
                .userName("testA")
                .password("qQwerty)(123456&")
                .build();

        String response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(loginDemoqaDTO)
                        .when()
                        .post(EndPointsDemoqa.LOGIN_DEMOQA)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message " , "\"Congrats.\"", response);



//        PostDTO[] expectedLoginRespDto = {
//                LoginRespHwTwoDTO.builder()
////                        .title(createPostDTO.getTitle())  // createPostDTO - це шаблон, з якого викликаємо очікувані результати
////                        .body(createPostDTO.getBody())
////                        .select1(createPostDTO.getSelect1())
////                        .uniquePost(createPostDTO.getUniquePost())
////                        .isVisitorOwner(false)
////                        .author(AuthorDTO.builder().username(ApiHelper.USER_NAME).build())
//
//                        .userId()
//                        .username()
//                        .password()
//                        .token()
//                        .expires()
//                        .created_date()
//                        .isActive()
//                        .build()
//        };

    }
}