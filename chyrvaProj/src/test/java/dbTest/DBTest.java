package dbTest;

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

public class DBTest {
   private Database mysqlDB;
    private Logger log = Logger.getLogger(DBTest.class);

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();

    }

    @After
    public void tearDown() throws SQLException {
        mysqlDB.quit();

    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G6_chyrvaV";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mysqlDB.selectTableAsMap(
                        String.format("Select * FROM seleniumTable WHERE login = '%s'", LOGIN));
        log.info(dataFromSeleniumTable);
       // log.info(dataFromSeleniumTable.get(1).get("login"));
        log.info("Number of rows = " + dataFromSeleniumTable.size());
        int numberOfRows = mysqlDB.changeTable("INSERT INTO seleniumTable VALUES(32,'%s', '123456')",LOGIN);
        log.info("Number of inserted rows = "+ numberOfRows);

        dataFromSeleniumTable =
                mysqlDB.selectTableAsMap(
                        String.format("Select * FROM seleniumTable WHERE login = '%s'", LOGIN));
        log.info(dataFromSeleniumTable);

        int deletedRows = mysqlDB.changeTable("DELETE from seleniumTable WHERE login = '%s'",LOGIN);
        log.info("Number of deleted rows = " + deletedRows);


        log.info("------");

        DB_Util db_util = new DB_Util();
        log.info(db_util.getPassForLogin("G5_taras"));




    }
}
