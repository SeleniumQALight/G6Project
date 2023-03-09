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
    private Database mySqlDB;
    private Logger log = Logger.getLogger(DBTest.class);

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySqlDB = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        mySqlDB.quit();
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G6_Emma";
        ArrayList<Map<String, String>> dataFromSeleniumTable
                = mySqlDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        log.info(dataFromSeleniumTable);
    //    log.info(dataFromSeleniumTable.get(1).get("login"));
        log.info("Data of rows = " + dataFromSeleniumTable.size());

        int numberOfRows = mySqlDB.changeTable("INSERT INTO seleniumTable VALUES(12, '%s', 'pass12')", LOGIN);
        log.info("Number of inserted rows = " + numberOfRows);

        dataFromSeleniumTable
                = mySqlDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        log.info(dataFromSeleniumTable);

        int deletedRows = mySqlDB.changeTable("DELETE from seleniumTable WHERE login = '%s'", LOGIN);
        log.info("Number of deleted rows = " + deletedRows);

        log.info("------");
        DB_Util db_util = new DB_Util();
        log.info(db_util.getPassForLogin("G5_taras"));

    }

}
