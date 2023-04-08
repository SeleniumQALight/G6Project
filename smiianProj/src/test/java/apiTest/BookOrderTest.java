
package apiTest;

import api.ApiHelper;
import api.ApiHelperBook;
import api.dto.responseDto.LoginRespHwTwoDTO;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BookOrderTest {

    ApiHelperBook apiHelperBook = new ApiHelperBook();
    Logger logger = Logger.getLogger(getClass());
//    String isbn;

    @Test
    public void loginDemoqa() {

//2. логінетесь цим юзером через апішку POST
        LoginRespHwTwoDTO respLoginDTO = apiHelperBook.loginByUser();
        logger.info(respLoginDTO.getToken());
        logger.info(respLoginDTO.getUserId());
//3. З респонса логіна зберігаєте token і userId
        String token = respLoginDTO.getToken();
        String userId = respLoginDTO.getUserId();

//4. Видаляєте всі книжки данного юзера апішкою
        apiHelperBook.deleteAllBooksById(respLoginDTO.getUserId(), token);

//5. Отримуете повний список книжок АПІшкою GET
        logger.info(apiHelperBook.getAllBooks(token)[0]);
//        String firstBookIsbn = apiHelperBook.getAllBooks(token); //[0];         //  як дістати код першої книги із запита?

//6. Додаете книжку юзеру АПІшкою POST
//        apiHelperBook.addBooksToUser(token, userId, firstBookIsbn);

//7. Перевіряєте що тепер у юзера є одна книжка і її номер той, що вказували при додаванні.
        String getIsbn = apiHelperBook.getUsersBooks(token, userId).getIsbn();   // що повертаєм, стрінгу??!

//        Assert.assertEquals("Number of posts ", firstBookIsbn, getIsbn);


    }
}