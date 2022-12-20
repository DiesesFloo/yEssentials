package systems.floo.yessentials.messages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import systems.floo.yessentials.config.ConfigProvider;

public class MessageProvider {

    private static final YamlConfiguration CONFIG = ConfigProvider.getCustomConfig("strings.yml");

    /**
     * Returns the main prefix defined in config
     *
     * @return The prefix
     */
    public static String getMainPrefix() {
        return getPrefix("main");
    }

    /**
     * Returns the prefix defined with the key
     * in params from the config
     *
     * @param key They key of the prefix
     * @return The prefix with the given key
     */
    public static String getPrefix(String key){
        return ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.prefixes." + key));
    }

    /**
     * Returns the message defined with the key
     * in params from the config
     *
     * @param key The key of the message
     * @return The message with the given key
     */
    public static String getMessage(String key) {
        return getMainPrefix()
                + ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.messages." + key));
    }

}
