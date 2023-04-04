package apiTests;

import api.ApiHelperBooks;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class ApiTestBooks {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    Logger logger = Logger.getLogger(getClass());


    @Test
    public void checkBookStore(){
        String token = apiHelperBooks.getToken();

    }

}
