package baseTest;

import dbtest.DBTest;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import java.sql.SQLException;

public class DBselenuimUsers {
    String myUniqueTitle;

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


}




