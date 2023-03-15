package dbTest;

import libraries.DB_Util;
import libraries.Database;
import libraries.MySQL_Database;
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
        final String LOGIN = "G6_yuliia";

        ArrayList<Map<String , String>> dataFromSeleniumTable = mysqlDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        log.info(dataFromSeleniumTable);
       // log.info(dataFromSeleniumTable.get(1).get("login"));
        log.info("Number of rows = "+dataFromSeleniumTable.size());

        int numberOfRows = mysqlDB.changeTable("INSERT INTO seleniumTable VALUES (555, '%s', 'asdfg123')", LOGIN);
        log.info("Number of inserted rows = " + numberOfRows);

        dataFromSeleniumTable = mysqlDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        log.info(dataFromSeleniumTable);

        int deleteRows = mysqlDB.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        log.info("Number of deleted rows = " + deleteRows);

        log.info("---------------------");
        DB_Util db_util = new DB_Util();
        log.info(  db_util.getPassForLogin("G5_taras"));
    }
}
