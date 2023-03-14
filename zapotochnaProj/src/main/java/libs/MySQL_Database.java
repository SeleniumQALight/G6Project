package libs;

import java.sql.SQLException;
import org.aeonbits.owner.ConfigFactory;

public class MySQL_Database {
    private static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    public static Database getDataBase() throws SQLException, ClassNotFoundException {
        return new Database(
                configProperties.MySQL() //створємо новий об'єкт,якому кажете йди дістань ключ..
                , configProperties.MySQL_DB()
                , configProperties.MySQL_DB_USER()
                , configProperties.MySQL_DB_PASSWORD());
    }
}
