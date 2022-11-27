package systems.floo.yessentials.commands.godmode;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class GodModeCommandProvider {

    private static ArrayList<UUID> godPlayers = new ArrayList<>();

    public static ArrayList<UUID> getGodPlayers() {
        return godPlayers;
    }

    public static boolean isGodPlayer(Player p){
        return godPlayers.contains(p.getUniqueId());
    }

    public static void addGodPlayer(Player p){
        godPlayers.add(p.getUniqueId());
    }

    public static void removeGodPlayer(Player p){
        godPlayers.remove(p.getUniqueId());
    }
}
