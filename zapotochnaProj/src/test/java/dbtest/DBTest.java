package dbtest;

import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class DBTest {
    private Database mysqlDB;
    private Logger log = Logger.getLogger(DBTest.class);
    @Before
    public void setUP() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();
    }


    @After
    public void tearDown() throws SQLException {

        mysqlDB.quit();
    }


    @Test
    public void testDataFromDb() throws SQLException {

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mysqlDB.selectTableAsMap("SELECT * FROM seleniumTable  ");
        log.info(dataFromSeleniumTable);
        log.info(dataFromSeleniumTable.get(1).get("login"));

        log.info("number of rows = " + dataFromSeleniumTable.size());



    }
}
