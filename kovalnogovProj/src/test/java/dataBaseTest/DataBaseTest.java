package dataBaseTest;

import io.netty.util.internal.logging.Log4J2LoggerFactory;
import libs.DB_Util;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DataBaseTest {
    private Database mySqlDB;
    private Logger log = Logger.getLogger(DataBaseTest.class);

    @Before
    public void setUp() throws Exception {
        mySqlDB = MySQL_Database.getDataBase();
    }

    @Test
    public void testDataBase() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G6_Kovalnohov";

        ArrayList<Map<String, String>> maps = mySqlDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login='%s'", LOGIN));
        log.info(maps);

        int insertedRows = mySqlDB.changeTable("INSERT INTO seleniumTable VALUES (8564563,'%s','2345')", LOGIN);
        log.info("Number of inserted rows =" + insertedRows);

        maps = mySqlDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login='%s'", LOGIN));
        log.info(maps);

        int deletedRows= mySqlDB.changeTable("DELETE FROM seleniumTable WHERE login='%s'",LOGIN);
        log.info("Number of deleted rows ="+deletedRows);


        log.info("____________________");
        DB_Util db_util=new DB_Util();
      //  db_util.getPassForLogin("G5_taras");
        log.info(db_util.getPassForLogin("G5_taras"));
       /* List<Map<String, String>> dataFromSeleniumTable = mySqlDB.selectTableAsMap("SELECT * FROM seleniumTable");
        log.info(dataFromSeleniumTable);
        log.info(dataFromSeleniumTable.get(1).get("login"));
        log.info("Numbers of rows:" + dataFromSeleniumTable.size());

        List<Map<String, String>> dataFromSeleniumTableWithWhere = mySqlDB.selectTableAsMap("SELECT * FROM seleniumTable WHERE idNumber=899");
        log.info(dataFromSeleniumTableWithWhere);*/

    }

    @After
    public void tearDown() throws Exception {
        mySqlDB.quit();
    }
}
