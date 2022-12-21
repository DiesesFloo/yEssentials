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
        return getPrefix(PrefixType.MAIN);
    }

    /**
     * Returns the prefix defined with the key
     * in params from the config
     *
     * @param key The key of the prefix
     * @return The prefix with the given key
     */
    public static String getPrefix(String key) {
        return ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.prefixes." + key));
    }

    /**
     * Returns the prefix defined with
     * the key of the prefix type
     *
     * @param prefixType The type of the prefix
     * @return The prefix of the given type
     */
    public static String getPrefix(PrefixType prefixType) {
        return getPrefix(prefixType.getKey());
    }

    /**
     * Returns the message defined with the key
     * in params from the config
     *
     * @param key The key of the message
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key) {
        return getMainPrefix()
                + ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.messages." + key));
    }

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key) {
        return getPrefix(prefixType.getKey())
                + ChatColor.translateAlternateColorCodes('&',
                CONFIG.getString("strings.messages." + key));
    }

}
