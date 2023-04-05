
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










//        public AddBookToUserHw






    }
}