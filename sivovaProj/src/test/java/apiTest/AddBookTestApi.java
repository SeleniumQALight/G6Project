package apiTest;

import api.APIHelper;
import api.APIHelperBooks;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class AddBookTestApi {
    APIHelperBooks apiHelper = new APIHelperBooks();
    Logger logger = Logger.getLogger(getClass());
    public static final String USER_NAME = "ApiTestUser";
    private final String PASSWORD = "Qwerty_123456@";

    @Before
    public void DeleteAllBooksForUser() {
        apiHelper.deleteBooksTillPresent(USER_NAME,PASSWORD);
    }


    @Test
    public void AddBookForUserByApi(){

        String token = apiHelper.getToken(USER_NAME,PASSWORD);
        String userId = apiHelper.getUserId(USER_NAME,PASSWORD);

    }

}
