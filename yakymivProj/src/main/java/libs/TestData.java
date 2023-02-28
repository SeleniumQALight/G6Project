package libs;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static ConfigHiddenProperties configHiddenProperties
            = ConfigFactory.create(ConfigHiddenProperties.class);
    public final static String VALID_LOGIN
            = System.getProperty("userName", configHiddenProperties.login());
    public final static String VALID_PASSWORD
            = System.getProperty("password", configHiddenProperties.password());


    public final static String INVALID_LOGIN = "qaauto123";
    public final static String INVALID_PASSWORD = "12345qwerty";
}
