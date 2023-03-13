package libs;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import java.sql.SQLException;

public class DBselenuimUsersFromTable {


    private Database mysqlDB;
    private Logger logger = Logger.getLogger(DBselenuimUsersFromTable.class);


    @Before
    public void setUP() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();
    }


    public String getPassFromDBForLogin(String LOGIN) throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = mysqlDB.selectValue(
                String.format("SELECT password FROM seleniumUsers WHERE login = '%s'", LOGIN)
        );
        mysqlDB.quit();


        logger.info("--- Disconnected from DB -------");
        return pass;
    }


    @After
    public void tearDown() throws SQLException {

        mysqlDB.quit();
    }
}





