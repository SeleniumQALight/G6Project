package libs;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:./src/main/java/resources/hiddenConfig.properties")
public interface ConfigHiddenProperties extends Config {
    String login();

    String password();

    String loginDB();
}
