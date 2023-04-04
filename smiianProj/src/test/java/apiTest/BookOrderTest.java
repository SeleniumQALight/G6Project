
package apiTest;

import api.ApiHelperBook;
import api.EndPoints;
import api.EndPointsDemoqa;
import api.dto.requestDto.LoginReqHwTwoDemoqaDTO;
import api.dto.responseDto.GetAllBooksRefHwTwoDTO;
import api.dto.responseDto.LoginRespHwTwoDTO;
import api.dto.responseDto.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BookOrderTest {

    ApiHelperBook apiHelperBook = new ApiHelperBook();
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void loginDemoqa() {

        LoginRespHwTwoDTO respLoginDTO = apiHelperBook.loginByUser();

        logger.info(respLoginDTO.getToken());
        logger.info(respLoginDTO.getUserId());



//    public GetAllBooksRefHwTwoDTO[] getAllBooks() {
//            GetAllBooksRefHwTwoDTO[] respGetAllBooksDTO =
//         given()
//                    .contentType(ContentType.JSON)
//                    .log().all()
//                    .auth().oauth2(respLoginDTO.getToken())
//                 .when()
//                    .get(EndPointsDemoqa.GET_ALL_BOOKS)
//                 .then()
//                    .statusCode(200)
//                    .log().all()
//                    .extract().response().getBody().as(GetAllBooksRefHwTwoDTO[].class);
//            return respGetAllBooksDTO[];
//        }


//        logger.info(respGetAllBooksDTO[0].getisbn());
//
//
//
//        private void deleteAllBookById(String userId, String message) {
//            JSONObject requestParams = new JSONObject();
//            requestParams.put("userId", userId);
//            requestParams.put("message", message);
//
//            String respDeleteAllBooks =
//                    given()
////                            .contentType(ContentType.JSON)
////                            .log().all()
//                            .spec(requestSpecification)
//                            .body(bodyParams.toMap())
//                         .when()
//                            .delete(EndPointsDemoqa.DELETE_ALL_BOOKS, userId)
//                         .then()
//                            .statusCode(200)
//                            .log().all()
//                            .extract().response().getBody().asString();
////            Assert.assertEquals("Message " , "\"Success\"", response);
//        }



    }
}