package libs;

import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;

public class TestData {
    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);
    public final static String VALID_LOGIN =
            System.getProperty("login", configHiddenProperties.login());
    public final static String VALID_PASSWORD =
            System.getProperty("password", configHiddenProperties.password());
    public final static String VALID_LOGIN_DB =
            System.getProperty("login", configHiddenProperties.loginDB());

    public static Double buyKursAPI;
    public static Double sellKursAPI;
    public static Double buyKursUI;
    public static Double sellKursUI;


}
