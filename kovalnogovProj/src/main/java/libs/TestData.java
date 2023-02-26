package libs;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static ConfigHiddenProperties hidenProperties = ConfigFactory.create(ConfigHiddenProperties.class);
    public static final String VALID_LOGIN =System.getProperty("login", hidenProperties.login());
    public static final String VALID_PASSWORD = System.getProperty("password", hidenProperties.password());
}
