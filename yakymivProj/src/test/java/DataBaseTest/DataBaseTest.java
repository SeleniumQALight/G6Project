package DataBaseTest;

import libs.DB_Util;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseTest {
    private Database mySQLDB;
    private Logger log = Logger.getLogger(DataBaseTest.class);

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySQLDB = MySQL_Database.getDataBase();
    }

    @Test
    public void testDataFromDb() throws SQLException, ClassNotFoundException {

        final String LOGIN = "G6_ivan";


        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQLDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        log.info(dataFromSeleniumTable);
        //  log.info(dataFromSeleniumTable.get(1).get("login"));

        log.info("number of rows = " + dataFromSeleniumTable.size());

        int numberOfRows = mySQLDB.changeTable("INSERT INTO seleniumTable VALUES(90, '%s', '09766')", LOGIN);

        log.info("number of inserted rows " + numberOfRows);

        dataFromSeleniumTable =
                mySQLDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        log.info(dataFromSeleniumTable);

        int deletedRows = mySQLDB.changeTable("DELETE from seleniumTable  WHERE login = '%s'", LOGIN);


        log.info(deletedRows + " = number of deleted rows");


        log.info("---------");

        DB_Util db_util = new DB_Util();

        log.info(db_util.getPassForLogin("G5_taras"));

    }



    @After
    public void tearDown() throws SQLException {
        mySQLDB.quit();
    }
}
