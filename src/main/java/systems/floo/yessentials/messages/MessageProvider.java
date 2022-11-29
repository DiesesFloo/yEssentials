package systems.floo.yessentials.messages;

import org.bukkit.configuration.file.YamlConfiguration;
import systems.floo.yessentials.config.ConfigProvider;

public class MessageProvider {

    private static final YamlConfiguration CONFIG = ConfigProvider.getCustomConfig("strings.yml");

    public static String getPrefix(){
        return CONFIG.getString("strings.prefix");
    }

    public static String getMessage(String key){
        return getPrefix() + CONFIG.getString("strings.messages." + key);
    }

}
