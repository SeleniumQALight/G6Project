package libs;

import com.google.common.cache.AbstractCache;
import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;

public class TestData {
    public static ConfigHiddenProperties configHiddenProperties
            = ConfigFactory.create(ConfigHiddenProperties.class);
    public final static String VALID_LOGIN = System.getProperty("login", configHiddenProperties.login());

    public final static String VALID_PASSWORD = System.getProperty("password", configHiddenProperties.password());

    public final static String INVALID_USERNAME = "tr";
    public final static String INVALID_EMAIL = "test.com";
    public final static String INVALID_PASSWORD = "123";

    public static final String SELL_API = "sellAPI";
    public static final String BUY_API = "buyAPI";
    public static final String SELL_UI = "sellUI";
    public static final String BUY_UI = "buyUI";

    public static HashMap<String, Double> privatCurrency = new HashMap<>();

}
