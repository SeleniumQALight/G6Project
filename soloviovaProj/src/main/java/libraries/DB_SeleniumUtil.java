package libraries;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_SeleniumUtil {
    private Database mySql_DB;
    Logger logger = Logger.getLogger(getClass());

    public String getLoginPassword(String login) throws SQLException, ClassNotFoundException {
        mySql_DB = MySQL_Database.getDataBase();
        logger.info("----- Connected to DB ------");

        String pass = mySql_DB.selectValue(String.format("select passWord from seleniumUsers where login = '%s'", login));

        mySql_DB.quit();
        logger.info("----- Disconnected from DB ---------");
        return pass;
    }

}
