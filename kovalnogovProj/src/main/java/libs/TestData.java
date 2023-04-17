package libs;

import api.privateBank.CurrencyDTO;
import org.aeonbits.owner.ConfigFactory;

import java.util.Map;

public class TestData {
    public static ConfigHiddenProperties hidenProperties = ConfigFactory.create(ConfigHiddenProperties.class);
    public static final String VALID_LOGIN =System.getProperty("login", hidenProperties.login());
    public static final String VALID_PASSWORD = System.getProperty("password", hidenProperties.password());
    public static Map<String, CurrencyDTO> currencyFromUI;
    public static Map<String, CurrencyDTO> currencyFromAPI;
}
