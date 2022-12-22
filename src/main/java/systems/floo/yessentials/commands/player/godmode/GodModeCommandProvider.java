package systems.floo.yessentials.commands.player.godmode;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class GodModeCommandProvider {

    private static ArrayList<UUID> godPlayers = new ArrayList<>();

    /**
     * Returns the list of the players in god mode
     *
     * @return List of players in god mode
     */
    public static ArrayList<UUID> getGodPlayers() {
        return godPlayers;
    }

    /**
     * Checks if a player is in god mode
     *
     * @param p The player to check if he's in god mode
     * @return Returns if the player is in god mode
     */
    public static boolean isGodPlayer(Player p) {
        return godPlayers.contains(p.getUniqueId());
    }

    /**
     * Adds a player to the god mode player list
     *
     * @param p The player to add
     */
    public static void addGodPlayer(Player p) {
        godPlayers.add(p.getUniqueId());
    }

    /**
     * Removes a player from the god mode player list
     *
     * @param p The player to remove
     */
    public static void removeGodPlayer(Player p) {
        godPlayers.remove(p.getUniqueId());
    }
}
