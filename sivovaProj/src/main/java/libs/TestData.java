package libs;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);

    public final static String VALID_LOGIN = System.getProperty("login", configHiddenProperties.login());
    public final static String VALID_PASSWORD = System.getProperty("password", configHiddenProperties.password());
    public final static String INVALID_USERNAME = "tr";
    public final static String ERROR_MESSAGE_USERNAME = "Username must be at least 3 characters.";
    public final static String INVALID_PASSWORD = "123";
    public final static String INVALID_EMAIL = "test.com";
    public final static String ERROR_MESSAGE_EMAIL = "You must provide a valid email address.";
    public final static String ERROR_MESSAGE_PASSWORD = "Password must be at least 12 characters.";


}
