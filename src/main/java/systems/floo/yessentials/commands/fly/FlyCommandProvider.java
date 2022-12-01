package systems.floo.yessentials.commands.fly;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class FlyCommandProvider {

    private static ArrayList<UUID> flyers = new ArrayList<>();

    /**
     * Returns the list of the players in fly mode
     * @return List of players in fly mode
     */
    public static ArrayList<UUID> getFlyers() {
        return flyers;
    }

    public static boolean isFlyer(Player p){
        return flyers.contains(p.getUniqueId());
    }

    public static void addFlyer(Player p){
        flyers.add(p.getUniqueId());
        p.setAllowFlight(true);
        p.setFlying(true);
    }

    public static void removeFlyer(Player p){
        flyers.remove(p.getUniqueId());
        p.setAllowFlight(false);
        p.setFlying(false);
    }
}
