package systems.floo.yessentials.economy;

import org.bukkit.configuration.file.YamlConfiguration;
import systems.floo.yessentials.config.ConfigProvider;

import java.util.UUID;

public class EconomyProvider {

    private static final YamlConfiguration CONFIG = ConfigProvider.getCustomConfig("economy.yml");

    /**
     * Sets the coins of a player to the given amount
     *
     * @param uuid   The {@link UUID} of the player
     * @param amount The amount to set
     */
    public static void setCoins(UUID uuid, double amount) {
        CONFIG.set(uuid.toString(), amount);
    }

    /**
     * Returns the coins of a player
     *
     * @param uuid The {@link UUID} of the player
     * @return The amount of coins of the player
     */
    public static double getCoins(UUID uuid) {
        if (!isRegistered(uuid)) return 0;
        return CONFIG.getDouble(uuid.toString());
    }

    /**
     * Adds the given amount of coins to the player
     *
     * @param uuid   The {@link UUID} of the player
     * @param amount The amount to add
     */
    public static void addCoins(UUID uuid, double amount) {
        setCoins(uuid, getCoins(uuid) + amount);
    }

    /**
     * Removes the given amount of coins to the player
     *
     * @param uuid   The {@link UUID} of the player
     * @param amount The amount to remove
     */
    public static void removeCoins(UUID uuid, double amount) {
        setCoins(uuid, getCoins(uuid) - amount);
    }

    /**
     * Checks if the player has the amount of coins given
     * in the args
     *
     * @param uuid   The {@link UUID} of the player
     * @param amount The amount to check
     * @return If the player has the amount of coins given
     */
    public static boolean has(UUID uuid, double amount) {
        return getCoins(uuid) >= amount;
    }

    /**
     * Returns if the player is registered in the config
     *
     * @param uuid The {@link UUID} of the player
     * @return If the player is registered
     */
    public static boolean isRegistered(UUID uuid) {
        if (uuid == null) return false;
        return CONFIG.contains(uuid.toString());
    }

    /**
     * Resets all coins of a player
     *
     * @param uuid The {@link UUID} of the player
     */
    public static void resetCoins(UUID uuid) {
        if (!isRegistered(uuid)) return;
        setCoins(uuid, 0);
    }

    /**
     * Registers a player in the config
     *
     * @param uuid The {@link UUID} of the player
     */
    public static void register(UUID uuid) {
        if (isRegistered(uuid)) return;
        CONFIG.set(uuid.toString(), 0);
    }

}
