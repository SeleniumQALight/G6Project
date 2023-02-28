package libs;

import org.aeonbits.owner.ConfigFactory;

public class TestData {     //створюємо данні, які будуть використовуватись багато разів
    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);
    public final static String VALID_LIGIN = System.getProperty("login", configHiddenProperties.login());           // назва капсом для константи
    public final static String VALID_PASSWORD = System.getProperty("password", configHiddenProperties.password());   //  System.getProperty вставляє пароль "password"
                                                                                                   // а якщо небуде що вставляти візьме configHiddenProperties.password()

}
