package systems.floo.yessentials.messages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import systems.floo.yessentials.config.ConfigProvider;

public class MessageProvider {

    private static final YamlConfiguration CONFIG = ConfigProvider.getCustomConfig("strings.yml");

    /**
     * Returns the default prefix defined in config
     *
     * @return The prefix
     */
    public static String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.prefix"));
    }

    /**
     * Returns the message defined with the key
     * in params from the config
     *
     * @param key The key of the message
     * @return The message with the defined key
     */
    public static String getMessage(String key) {
        return getPrefix()
                + ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.messages." + key));
    }

}
