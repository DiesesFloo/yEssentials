package systems.floo.yessentials.messages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import systems.floo.yessentials.config.ConfigProvider;

public class MessageProvider {

    private static final YamlConfiguration CONFIG = ConfigProvider.getCustomConfig("strings.yml");

    public static String getPrefix(){
        return ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.prefix"));
    }

    public static String getMessage(String key){
        return getPrefix()
                + ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.messages." + key));
    }

}
