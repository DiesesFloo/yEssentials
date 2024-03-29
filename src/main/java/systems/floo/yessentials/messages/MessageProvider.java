package systems.floo.yessentials.messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import systems.floo.yessentials.config.ConfigProvider;
import systems.floo.yessentials.economy.EconomyProvider;

import java.text.NumberFormat;
import java.util.Locale;

public class MessageProvider {

    private static final YamlConfiguration CONFIG = ConfigProvider.getCustomConfig("strings.yml");
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(Locale.ENGLISH);

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
        if (key == "none") return "";
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
        if (prefixType == PrefixType.NONE) return "";
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
     * in params from the config and replaces given
     * placeholders
     *
     * @param key   The key of the message
     * @param name1 The name of the command executor
     * @param name2 The name of the command target
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key, String name1, String name2) {
        return getMessage(key)
                .replace("%player1%", name1)
                .replace("%player2%", name2);
    }

    /**
     * Returns the message defined with the key
     * in params from the config and replaces given
     * placeholders
     *
     * @param key     The key of the message
     * @param player1 The command executor
     * @param player2 The command target
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key, CommandSender player1, CommandSender player2) {
        return getMessage(key, player1.getName(), player2.getName());
    }

    /**
     * Returns the message defined with the key
     * in params from the config and replaces given
     * placeholders
     *
     * @param key  The key of the message
     * @param name The name of the command executor
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key, String name) {
        return getMessage(key).replace("%player%", name);
    }

    /**
     * Returns the message defined with the key
     * in params from the config and replaces given
     * placeholders
     *
     * @param key    The key of the message
     * @param player The command executor
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key, CommandSender player) {
        return getMessage(key, player.getName());
    }

    /**
     * Returns the message defined with the key
     * in params from the config and replaces given
     * placeholders
     *
     * @param key    The key of the message
     * @param name   The name of the command executor
     * @param amount The amount of money
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key, String name, double amount) {
        return getMessage(key, name)
                .replace("%amount%", NUMBER_FORMAT.format(amount) + EconomyProvider.getCurrencySign());
    }

    /**
     * Returns the message defined with the key
     * in params from the config and replaces given
     * placeholders
     *
     * @param key    The key of the message
     * @param player The command executor
     * @param amount The amount of money
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key, CommandSender player, double amount) {
        return getMessage(key, player.getName(), amount);
    }

    /**
     * Returns the message defined with the key
     * in params from the config and replaces given
     * placeholders
     *
     * @param key    The key of the message
     * @param name1  The name of the command executor
     * @param name2  The name of the command target
     * @param amount The amount of money
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key, String name1, String name2, double amount) {
        return getMessage(key, name1, name2)
                .replace("%amount%", NUMBER_FORMAT.format(amount) + EconomyProvider.getCurrencySign());
    }

    /**
     * Returns the message defined with the key
     * in params from the config and replaces given
     * placeholders
     *
     * @param key     The key of the message
     * @param player1 The command executor
     * @param player2 The command target
     * @param amount  The amount of money
     * @return The message with the given key and main prefix
     */
    public static String getMessage(String key, CommandSender player1, CommandSender player2, double amount) {
        return getMessage(key, player1.getName(), player2.getName(), amount);
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

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type and replaces given
     * placeholders
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @param name1      The name of the command executor
     * @param name2      The name of the command target
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key, String name1, String name2) {
        return getMessage(prefixType, key)
                .replace("%player1%", name1)
                .replace("%player2%", name2);
    }

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type and replaces given
     * placeholders
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @param player1    The command executor
     * @param player2    The command target
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key, CommandSender player1, CommandSender player2) {
        return getMessage(prefixType, key, player1.getName(), player2.getName());
    }

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type and replaces given
     * placeholders
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @param name       The name of the command executor
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key, String name) {
        return getMessage(prefixType, key).replace("%player%", name);
    }

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type and replaces given
     * placeholders
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @param player     The command executor
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key, CommandSender player) {
        return getMessage(prefixType, key, player.getName());
    }

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type and replaces given
     * placeholders
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @param name       The name of the command executor
     * @param amount     The amount of money
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key, String name, double amount) {
        return getMessage(prefixType, key, name)
                .replace("%amount%", NUMBER_FORMAT.format(amount) + EconomyProvider.getCurrencySign());
    }

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type and replaces given
     * placeholders
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @param player     The command executor
     * @param amount     The amount of money
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key, CommandSender player, double amount) {
        return getMessage(prefixType, key, player.getName(), amount);
    }

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type and replaces given
     * placeholders
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @param name1      The name of the command executor
     * @param name2      The name of the command target
     * @param amount     The amount of money
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key, String name1, String name2, double amount) {
        Bukkit.getLogger().info(amount + EconomyProvider.getCurrencySign());
        return getMessage(prefixType, key, name1, name2)
                .replace("%amount%", NUMBER_FORMAT.format(amount) + EconomyProvider.getCurrencySign());
    }

    /**
     * Returns the message defined with the key
     * in params from the config and with the
     * given prefix type and replaces given
     * placeholders
     *
     * @param prefixType The type of the prefix
     * @param key        The key of the message
     * @param player1    The command executor
     * @param player2    The command target
     * @param amount     The amount of money
     * @return The message with the given key and given prefix
     */
    public static String getMessage(PrefixType prefixType, String key, CommandSender player1, CommandSender player2, double amount) {
        return getMessage(prefixType, key, player1.getName(), player2.getName(), amount);

    }
}
