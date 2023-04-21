package libs;

import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;

public class TestData {
    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);
    public final static String VALID_LOGIN = System.getProperty("login", configHiddenProperties.login());
    public final static String VALID_PASSWORD = System.getProperty("password", configHiddenProperties.password());

    public static final String SELL_KEY_API = "sellAPI";
    public static final String BUY_KEY_API = "buyAPI";
    public static final String SELL_KEY_UI = "sellUI";
    public static final String BUY_KEY_UI = "buyUI";

    public static HashMap<String, Double> currencyPB = new HashMap<>();
}
