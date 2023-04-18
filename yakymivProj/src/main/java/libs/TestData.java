package libs;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static ConfigHiddenProperties configHiddenProperties
            = ConfigFactory.create(ConfigHiddenProperties.class);
    public final static String VALID_LOGIN
            = System.getProperty("userName", configHiddenProperties.login());
    public final static String VALID_PASSWORD
            = System.getProperty("password", configHiddenProperties.password());


    public final static String INVALID_LOGIN = "qa";
    public final static String INVALID_PASSWORD = "123";
    public final static String INVALID_EMAIL = "qa.com";

    public final static String USERNAME = "ivan19";
    public final static String PASSWORD = "6556dififafo";
}
