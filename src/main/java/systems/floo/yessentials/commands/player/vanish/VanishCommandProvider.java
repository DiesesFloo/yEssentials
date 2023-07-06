package systems.floo.yessentials.commands.player.vanish;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.EssentialsPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class VanishCommandProvider {

    private static final JavaPlugin PLUGIN = EssentialsPlugin.getPlugin();

    @Getter
    private static ArrayList<UUID> vanishedPlayers = new ArrayList<>();

    /**
     * Checks if a player is in fly mode
     *
     * @param p The player to check if he's in fly mode
     * @return Returns if the player is in fly mode
     */
    public static boolean isVanished(Player p) {
        return vanishedPlayers.contains(p.getUniqueId());
    }

    /**
     * Sets a player into vanish mode
     *
     * @param p The player to vanish
     */
    public static void vanishPlayer(Player p) {
        vanishedPlayers.add(p.getUniqueId());
        for (Player all : Bukkit.getOnlinePlayers()){
            if (all.hasPermission("essentials.vanish.bypass")){
                continue;
            }

            all.hidePlayer(PLUGIN, p);
        }
    }

    /**
     * Unvanishs a player who is in vanish mode
     *
     * @param p The player to unvanish
     */
    public static void unvanishPlayer(Player p) {
        vanishedPlayers.remove(p.getUniqueId());
        for (Player all: Bukkit.getOnlinePlayers()){
            all.showPlayer(PLUGIN, p);
        }
    }

}
