package systems.floo.yessentials.commands.player.fly;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class FlyCommandProvider {

    @Getter
    private static ArrayList<UUID> flyers = new ArrayList<>();


    /**
     * Checks if a player is in fly mode
     *
     * @param p The player to check if he's in fly mode
     * @return Returns if the player is in fly mode
     */
    public static boolean isFlyer(Player p) {
        return flyers.contains(p.getUniqueId());
    }

    /**
     * Adds a player into the flyer list and sets him into fly mode
     *
     * @param p The player to add
     */
    public static void addFlyer(Player p) {
        flyers.add(p.getUniqueId());
        p.setAllowFlight(true);
        p.setFlying(true);
    }

    /**
     * Removes a player from the flyer list and sets him back into normal mode
     *
     * @param p The player to remove
     */
    public static void removeFlyer(Player p) {
        flyers.remove(p.getUniqueId());
        p.setAllowFlight(false);
        p.setFlying(false);
    }
}
