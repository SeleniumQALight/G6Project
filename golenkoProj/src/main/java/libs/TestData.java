package libs;

import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;

public class TestData {
    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);
    public final static String VALID_LOGIN = System.getProperty("login", configHiddenProperties.login());
    public final static String VALID_PASSWORD = System.getProperty("password", configHiddenProperties.password());
    public final static String INVALID_USERNAME = "tr";
    public final static String INVALID_PASSWORD = "123";
    public final static String INVALID_EMAIL = "test.com";

    public static final String SELL_API_KEY = "sellAPI";
    public static final String BUY_API_KEY = "buyAPI";
    public static final String SELL_UI_KEY = "sellUI";
    public static final String BUY_UI_KEY = "buyUI";

    public static HashMap<String, Double> privatCurrencies = new HashMap<>();
}
