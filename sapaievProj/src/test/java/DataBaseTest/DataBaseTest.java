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
    private Database mysqlDB;
    private Logger logger=Logger.getLogger(DataBaseTest.class);

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        mysqlDB= MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        mysqlDB.quit();
    }

    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
        final String LOGIN="G6_Sapaiev";


        //Проверили , что юзера нет с новым именем
        ArrayList<Map<String,String>> dataFromSeleniumTable=
        mysqlDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login='%s'",LOGIN));
        logger.info(dataFromSeleniumTable);
        //logger.info(dataFromSeleniumTable.get(1).get("login"));
        logger.info("Number of rows= "+dataFromSeleniumTable.size());

        //создали нового пользователя в таблице и проверили
        int numberOfRows=mysqlDB.changeTable("INSERT INTO seleniumTable VALUES(777, '%s', '123456')",LOGIN);
        logger.info("Number of inserted rows= "+numberOfRows);
        dataFromSeleniumTable=
                mysqlDB.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login='%s'",LOGIN));
        logger.info(dataFromSeleniumTable);


        //удалили нового пользователя
        int deletedRows=mysqlDB.changeTable("DELETE from seleniumTable WHERE login='%s'",LOGIN);
        logger.info("Number of deleted rows= " +deletedRows);


        logger.info("------------------------------------");
        DB_Util db_util=new DB_Util();
        logger.info(db_util.getPassForLogin("G5_taras"));




    }




}
