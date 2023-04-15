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

    public static Double UI_BUY = 0.0;
    public static Double UI_SELL = 0.0;

    public static Double API_BUY = 0.0;
    public static Double API_SELL = 0.0;
}
