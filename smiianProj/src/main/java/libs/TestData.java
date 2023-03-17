package libs;

import org.aeonbits.owner.ConfigFactory;

public class TestData {     //створюємо данні, які будуть використовуватись багато разів
    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);

    public final static String VALID_LIGIN =                                      // назва капсом для константи
            System.getProperty("login", configHiddenProperties.login());      // login() - пов'язує з файлом ConfigHiddenProperties, а він
                                                                                   // в свою чергу, вов'язує з файлом hiddenConfig.properties, де вказані дані

    public final static String VALID_PASSWORD =
            System.getProperty("password", configHiddenProperties.password());   //  System.getProperty вставляє пароль "password"
                                                                                                   // а якщо небуде що вставляти візьме configHiddenProperties.password()

}
