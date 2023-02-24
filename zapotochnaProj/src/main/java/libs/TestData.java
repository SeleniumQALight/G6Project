package libs;

import org.aeonbits.owner.ConfigFactory;

public class TestData {

//коснтанти створюють назва з ВЕЛИКОЇЛІТЕРИ і нижнє підкреслення

//    public final static String VALID_LOGIN = "qaauto";
//    public final static String VALID_PASSWORD = "123456qwerty";

    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);
    public static String VALID_LOGIN = System.getProperty("login", configHiddenProperties.login());
    public static String VALID_PASSWORD = System.getProperty("password", configHiddenProperties.password());

}
