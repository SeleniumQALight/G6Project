package libraries;


import org.aeonbits.owner.ConfigFactory;

public class TestData {

    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);

    public final static String VALID_LOGIN = System.getProperty("login", configHiddenProperties.login());
    public final static String VALID_PAsSWORD = System.getProperty("password", configHiddenProperties.password());
    public final static String MESSAGE_1 = "Username must be at least 3 characters.";
    public final static String MESSAGE_2 = "You must provide a valid email address.";
    public final static String MESSAGE_3 = "Password must be at least 12 characters.";
    public final static String INVALID_PASSWORD = "Uttellusnisi,tristiquevelturpissitamet," +
            "accumsanporttitorligulaPraesentsemipsum," +
            "feugiatnonrisusut,mollismolestiearcu.Quisquehoncusorcinec" +
            " felispulvinaraconsequatarcublanditSedineugiatleoauctorsodales" +
            "nibh.InsitamethendrerittellusNaminrisussitametodioeuismodporttitor." +
            " MaecenasmolestieporttitortellusutultricesnislconsecteturacNammales" +
            "uadadictumdiamvitaeconvallis.Maecenasmolestieporttitortellusutultrices" +
            "nislconsecteturacNammalesMaecenasmolestieporttitortellusutultricesnislconsec" +
            "teturacNammalesMaecenasmolestieporttitortellusutultricesnislconsecteturacNam" +
            "uracNammalesMaecenasmolestieporttitortellusutultricesnislconsectetur" +
            "acNamm";
}
